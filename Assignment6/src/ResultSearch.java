class ResultSearch {
    private int start;//Starting index of the word found.
    private int stop;//Stopping index of the word found.
    private int count;//Number of words found.
    private boolean found;//Found or Not found.
    private String str;//search term.

    //Getter and Setter
    int getStart() {
        return start;
    }

    int getStop() {
        return stop;
    }

    void setStop(int stop) {
        this.stop = stop;
    }

    String getStr() {
        return str;
    }

    void setStr(String str) {
        this.str = str;
    }

    boolean isFound() {
        return found;
    }

    void setStart(int start) {
        this.start = start;
    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    void setFound(boolean found) {
        this.found = found;
    }
}
