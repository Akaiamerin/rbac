package com.rbac.v1.utils;
import java.util.stream.Stream;
public class StackMetadataWalkerUtils {
    private StackMetadataWalkerUtils() {

    }
    public static String getMethodName() {
        return StackWalker
                .getInstance()
                .walk((Stream<StackWalker.StackFrame> stackFrameStream)->{
                    return stackFrameStream
                            .skip(1)
                            .findFirst()
                            .map((StackWalker.StackFrame stackFrame)->{
                                return stackFrame.getMethodName();
                            })
                            .orElse(null);
                });
    }
    public static String getClassName() {
        return StackWalker
                .getInstance()
                .walk((Stream<StackWalker.StackFrame> stackFrameStream)->{
                    return stackFrameStream
                            .skip(1)
                            .findFirst()
                            .map((StackWalker.StackFrame stackFrame)->{
                                return stackFrame.getClassName();
                            })
                            .orElse(null);
                });
    }
}