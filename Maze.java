import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

public class Maze implements GridColors {
    private TwoDimGrid maze;

    // Constructor
    public Maze(TwoDimGrid m) {
        maze = m;
    }

    // ------------------- Problem 1 -------------------
    public boolean findMazePath(int x, int y) {
        // Out of bounds
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
            return false;

        Color color = maze.getColor(x, y);

        // Not a path (blocked or already visited)
        if (!color.equals(NON_BACKGROUND))
            return false;

        // Goal reached
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        }

        // Temporarily mark as path
        maze.recolor(x, y, PATH);

        // Try all 4 directions (right, down, left, up)
        if (findMazePath(x + 1, y) || findMazePath(x, y + 1) ||
            findMazePath(x - 1, y) || findMazePath(x, y - 1)) {
            return true;
        }

        // Dead end - backtrack
        maze.recolor(x, y, TEMPORARY);
        return false;
    }

    // Wrapper for GUI call
    public boolean findMazePath() {
        return findMazePath(0, 0);
    }

    // ------------------- Problem 2 -------------------
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(x, y, result, trace);
        if (result.isEmpty()) {
            result.add(new ArrayList<>()); // add empty list if no path
        }
        return result;
    }

    private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        // Out of bounds
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
            return;

        Color color = maze.getColor(x, y);
        // Skip if not valid path
        if (!color.equals(NON_BACKGROUND))
            return;

        // Add current position to path
        trace.push(new PairInt(x, y));

        // Goal found
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            ArrayList<PairInt> path = new ArrayList<>(trace);
            result.add(path);
            trace.pop();
            return;
        }

        // Mark visited
        maze.recolor(x, y, PATH);

        // Explore all 4 directions
        findMazePathStackBased(x + 1, y, result, trace);
        findMazePathStackBased(x - 1, y, result, trace);
        findMazePathStackBased(x, y + 1, result, trace);
        findMazePathStackBased(x, y - 1, result, trace);

        // Unmark and backtrack
        maze.recolor(x, y, NON_BACKGROUND);
        trace.pop();
    }

    // ------------------- Problem 3 -------------------
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<ArrayList<PairInt>> allPaths = findAllMazePaths(x, y);
        if (allPaths.isEmpty() || allPaths.get(0).isEmpty())
            return new ArrayList<>();

        ArrayList<PairInt> shortest = allPaths.get(0);
        for (ArrayList<PairInt> path : allPaths) {
            if (path.size() < shortest.size()) {
                shortest = path;
            }
        }
        return shortest;
    }
}
