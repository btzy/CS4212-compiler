class UnknownCharacterException extends Exception {
    public final String s;
    public final String filename;
    public final int left_line;
    public final int left_col;
    public final int right_line;
    public final int right_col;
    UnknownCharacterException(String unknownInput, String filename, int left_line, int left_col, int right_line, int right_col) {
        super("Unknown character \"" + unknownInput + "\"");
        this.s = unknownInput;
        this.filename = filename;
        this.left_line = left_line;
        this.left_col = left_col;
        this.right_line = right_line;
        this.right_col = right_col;
    }
}
