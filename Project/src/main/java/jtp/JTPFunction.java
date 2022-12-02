package jtp;

@FunctionalInterface
public interface JTPFunction {
    void proceed(JTPMessage message);
}
