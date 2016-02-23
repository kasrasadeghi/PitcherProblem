/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pitcherproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author DSTIGANT
 */
public class PitcherProblemSolver 
{
    protected static ArrayList<PitcherMove> DFSVisit( PitcherConfiguration visit, int goal, List<PitcherConfiguration> visited )
    {
        if (visited.contains(visit))
            return null;
        if (visit.hasAPitcherWithContents(goal))
            return new ArrayList<>();

        visited.add(visit);

        for (PitcherMove pm : visit.getPossibleMoves()) {
            PitcherConfiguration next = visit.executeMove(pm);
            ArrayList<PitcherMove> solution = DFSVisit(next, goal, visited);
            if (solution != null) {
                solution.add(0, pm);
                return solution;
            }
        }
        return null;
    }

    protected static ArrayList<PitcherMove> solveDFS( PitcherConfiguration pcs, int goal, List<PitcherConfiguration> visited ) {
        return DFSVisit(pcs, goal, visited);
    }

    public ArrayList<PitcherMove> solve(PitcherConfiguration inpc, int goal) {
        return solveDFS(inpc, goal, new ArrayList<>());
    }
}
