package me.xarta.xcustomclientname.config;

@FunctionalInterface
public interface SafeSupplier<T> {
    T get();
}