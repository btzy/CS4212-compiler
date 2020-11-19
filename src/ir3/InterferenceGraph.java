package ir3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Interference graph and register allocation mechanism.
 */

public class InterferenceGraph {
	private final boolean[][] adj_matrix;
	private final boolean[][] clobbers;
	private static final int NUM_REGS = 13; // 13 useable registers... 12 means LR here

	public InterferenceGraph(int sz) {
		adj_matrix = new boolean[sz][sz];
		clobbers = new boolean[sz][NUM_REGS];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (boolean[] adj_row : adj_matrix) {
			for (boolean x : adj_row) {
				sb.append(x ? '1' : '0');
			}
			sb.append('\n');
		}
		for (boolean[] clobber : clobbers) {
			for (boolean x : clobber) {
				sb.append(x ? '1' : '0');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	// TODO: what about params that are only used in the first statement?
	public static InterferenceGraph generate(LocalEnvironment env, int num_params, ArrayList<Instruction> insts) {
		// Build control flow graph (backlinks only)
		HashSet<Integer>[] backlinks = (HashSet<Integer>[]) new HashSet<?>[insts.size()+1];
		for (int i=0;i!=backlinks.length; ++i) {
			backlinks[i] = new HashSet<>();
		}
		ArrayList<Integer> label_map = new ArrayList<>();
		for (int i=0;i!=insts.size(); ++i) {
			Instruction inst = insts.get(i);
			if (inst instanceof Label) {
				int idx = ((Label)inst).index;
				while (label_map.size() <= idx) label_map.add(0);
				label_map.set(idx, i);
			}
		}
		for (int i=0;i!=insts.size(); ++i) {
			Instruction inst = insts.get(i);
			if (inst instanceof Goto) {
				int target_idx = ((Goto)inst).target.index;
				backlinks[label_map.get(target_idx)].add(i);
			}
			else if (inst instanceof IfGoto) {
				int target_idx = ((IfGoto)inst).target.index;
				backlinks[label_map.get(target_idx)].add(i);
				backlinks[i+1].add(i);
			}
			else if (inst instanceof Return) {}
			else {
				backlinks[i+1].add(i);
			}
		}
		backlinks[0].add(-1);

		// Do liveness analysis (BFS)
		HashSet<Integer>[] live_at_inst = (HashSet<Integer>[]) new HashSet<?>[insts.size()+1]; // this stores liveness at OUT position
		HashSet<Integer> live_at_start = new HashSet<>();
		for (int i=0;i!=live_at_inst.length; ++i) {
			live_at_inst[i] = new HashSet<>();
		}
		{
			final Queue<Integer> queue = new ArrayDeque<>();
			for (int i=0;i!=live_at_inst.length;++i) queue.add(i);
			Integer currline_box;
			while ((currline_box = queue.poll()) != null) {
				final int currline = currline_box;
				HashSet<Integer> new_live = new HashSet<>();
				if (currline != insts.size()) {
					final Instruction inst = insts.get(currline);
					OptionalInt opt_def = inst.getDef();
					opt_def.ifPresentOrElse(def -> {
						for (int x : live_at_inst[currline]) {
							if (x != def) new_live.add(x);
						}
					}, () -> {
						new_live.addAll(live_at_inst[currline]);
					});
					new_live.addAll(inst.getUses());
					//System.out.println(inst.getUses());
				}
				for (int bl : backlinks[currline]) {
					boolean need_update = false;
					HashSet<Integer> bl_live = bl == -1 ? live_at_start : live_at_inst[bl];
					for (int x : new_live) {
						if (bl_live.add(x)) need_update = true;
					}
					if (bl != -1 && need_update) queue.add(bl);
				}
			}
		}
		final InterferenceGraph graph = new InterferenceGraph(env.size());
		for (int i=0;i!=live_at_inst.length;++i){
			for (int x:live_at_inst[i]) {
				for (int y:live_at_inst[i]){
					if (x != y) {
						graph.adj_matrix[x][y] = true;
					}
				}
			}
		}
		for (int x:live_at_start) {
			for (int y:live_at_start) {
				if (x != y) {
					graph.adj_matrix[x][y] = true;
				}
			}
		}
		for (int i=0;i!=insts.size();++i){
			final Instruction inst = insts.get(i);
			OptionalInt opt_def = inst.getDef();
			final ArrayList<Integer> clobbered_regs = insts.get(i).getClobberedRegs();
			for (int x:live_at_inst[i]) {
				if (opt_def.isEmpty() || opt_def.getAsInt() != x) {
					boolean[] out = graph.clobbers[x];
					for (int y : clobbered_regs) {
						if (y == EmitFunc.Registers.LR) out[12] = true;
						else out[y] = true;
					}
				}
			}
			
		}
		return graph;
	}

	public EmitFunc.StorageLocation[] colour() {
		// when doing colouring, try to select the vertex with largest degree that is less than k
		// if not possible, then select the vertex with largest degree.

		// TODO: currently have a bug... cannot move the arguments around

		// the answer
		final EmitFunc.StorageLocation[] colours = new EmitFunc.StorageLocation[adj_matrix.length];

		final ArrayList<Integer> stack = new ArrayList<>();
		final boolean[] removed = new boolean[adj_matrix.length];
		while (true) {
			int best_vertex = -1;
			int best_choices = Integer.MAX_VALUE;
			for (int i=0; i!=adj_matrix.length;++i) {
				if (!removed[i]) {
					int curr_choices = 0;
					final boolean[] clobber = clobbers[i];
					for (int j=0; j!=NUM_REGS; ++j){
						if (!clobber[j]) ++curr_choices;
					}
					final boolean[] adj_row = adj_matrix[i];
					for (int j=0; j!=adj_row.length; ++j){
						if (adj_row[j]) --curr_choices;
					}
					if (curr_choices > 0) {
						if (curr_choices < best_choices) {
							best_vertex = i;
							best_choices = curr_choices;
						}
					}
				}
			}
			if (best_choices == Integer.MAX_VALUE) { // needs spill
				for (int i=0; i!=adj_matrix.length;++i) {
					if (!removed[i]) {
						int curr_choices = 0;
						final boolean[] clobber = clobbers[i];
						for (int j=0; j!=NUM_REGS; ++j){
							if (!clobber[j]) ++curr_choices;
						}
						final boolean[] adj_row = adj_matrix[i];
						for (int j=0; j!=adj_row.length; ++j){
							if (adj_row[j] && !removed[j]) --curr_choices;
						}
						if (curr_choices < best_choices) {
							best_vertex = i;
							best_choices = curr_choices;
						}
					}
				}
			}
			if (best_choices == Integer.MAX_VALUE) break; // G is empty

			stack.add(best_vertex);
			removed[best_vertex] = true;
		}
		while (!stack.isEmpty()) {
			final int vertex = stack.remove(stack.size() - 1);
			removed[vertex] = false;
			final boolean[] clobber = clobbers[vertex];
			final boolean[] avail_colours = new boolean[NUM_REGS];
			for (int i=0; i!=NUM_REGS; ++i) {
				if (!clobber[i]) avail_colours[i] = true;
			}
			final boolean[] adj_row = adj_matrix[vertex];
			for (int i=0; i!=adj_row.length; ++i) {
				if (!removed[i] && adj_row[i] && colours[i].isRegister) avail_colours[colours[i].value] = false;
			}
			int chosen_colour = -1;
			for (int i=0; i!=avail_colours.length; ++i) {
				if (avail_colours[i]) {
					chosen_colour = i;
					break;
				}
			}
			if (chosen_colour != -1) { // save in register
				final int reg = chosen_colour == 12 ? EmitFunc.Registers.LR : chosen_colour;
				colours[vertex] = EmitFunc.StorageLocation.makeRegister(reg);
			}
			else {
				colours[vertex] = EmitFunc.StorageLocation.makeMemLocal(0); // set to zero now... caller should fix it up later
			}
		}

		return colours;
	}
}

