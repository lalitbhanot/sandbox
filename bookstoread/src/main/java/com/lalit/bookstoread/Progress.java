package com.lalit.bookstoread;

public class Progress {
    private final int completed;
    private final int toRead;
    private final int inProgress;

    public Progress(int completed, int toRead, int inProgress) {
        this.completed = completed;
        this.toRead = toRead;
        this.inProgress = inProgress;
    }

    public int completed() {
        return this.completed;
    }

    public int toRead() {
        return this.toRead;
    }

    public int inProgress() {
        return this.inProgress;
    }
    public static Progress notStarted() {
        return new Progress(0, 0, 0);
    }
}