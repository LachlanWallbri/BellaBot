package com.unsw.bellabot;

import android.util.Pair;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import android.util.Log;

public class Tools {
    private static final String TAG = Tools.class.getName();

    /**
     * Executes a shell command with optional superuser (su) permissions.
     *
     * @param command The shell command to execute.
     * @param useSu   If true, executes the command with superuser (su) permissions; otherwise, uses standard shell (sh).
     * @return A Pair containing the exit value of the command and its output. The first element is the exit value,
     *         and the second element is the command output as a String. If an error occurs, the exit value is set to 1
     *         and the output is {@code null}.
     */
    public static Pair<Integer, String> execCommand(String command, boolean useSu) {
        // Log the command being executed and the privilege level
        Log.d(TAG, "execCommand:" + command + (useSu ? " with su" : " with sh"));

        // Prepare the command array: [command interpreter, -c, command]
        String[] strArr = new String[3];
        strArr[0] = useSu ? "su" : "sh";
        strArr[1] = "-c";
        strArr[2] = command;

        try {
            // Execute the command
            Process exec = Runtime.getRuntime().exec(strArr);

            // Wait for the process to complete with a timeout
            waitFor(30L, TimeUnit.SECONDS, exec);

            // Read the output from the process
            InputStream inputStream = exec.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();

            // Get the exit value of the process
            int exitValue = exec.exitValue();
            Log.d(TAG, "su exit value = " + exitValue + " " + sb2.toString());

            // Return the exit value and command output as a Pair
            return Pair.create(exitValue, sb2.toString());
        } catch (Throwable th) {
            // Log any exceptions that occur during execution
            Log.w(TAG, th);
            // Return an error code and null output in case of exception
            return Pair.create(1, null);
        }
    }

    /**
     * Waits for a process to complete or times out.
     *
     * @param timeout The maximum time to wait for the process to complete.
     * @param timeUnit The time unit of the timeout parameter (e.g., SECONDS, MILLISECONDS).
     * @param process The process to wait for.
     * @return {@code true} if the process completed within the timeout; {@code false} if the timeout was reached.
     * @throws InterruptedException If the current thread is interrupted while waiting.
     */
    public static boolean waitFor(long timeout, TimeUnit timeUnit, Process process) throws InterruptedException {
        // Record the start time in nanoseconds
        long nanoTime = System.nanoTime();
        // Convert the timeout to nanoseconds
        long nanos = timeUnit.toNanos(timeout);

        do {
            try {
                // Check if the process has completed
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                // Process is still running
                if (nanos > 0) {
                    // Sleep for the lesser of remaining time or 100 milliseconds
                    Thread.sleep(Math.min(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                // Update the remaining time
                nanos = timeUnit.toNanos(timeout) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }
}