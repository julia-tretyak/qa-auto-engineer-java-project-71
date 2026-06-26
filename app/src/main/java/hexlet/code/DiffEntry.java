package hexlet.code;

public final class DiffEntry {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public DiffEntry(
            final String entryKey,
            final Object entryOldValue,
            final Object entryNewValue,
            final String entryStatus) {
        this.key = entryKey;
        this.oldValue = entryOldValue;
        this.newValue = entryNewValue;
        this.status = entryStatus;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAdded() {
        return "added".equals(status);
    }

    public boolean isRemoved() {
        return "removed".equals(status);
    }

    public boolean isChanged() {
        return "changed".equals(status);
    }

    public boolean isUnchanged() {
        return "unchanged".equals(status);
    }
}
