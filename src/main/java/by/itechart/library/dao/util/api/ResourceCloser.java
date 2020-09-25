package by.itechart.library.dao.util.api;

public interface ResourceCloser {
    public void close(AutoCloseable resource);
}
