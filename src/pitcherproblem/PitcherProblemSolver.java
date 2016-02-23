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
    private PitcherConfiguration pc;

    public PitcherProblemSolver() {

    }

    public PitcherProblemSolver(PitcherConfiguration pc)
    {
        this.pc = pc;
    }
    
    protected static List<PitcherMove> DFSVisit( PitcherConfiguration visit, int goal, List<PitcherConfiguration> visited )
    {
        visited.add(visit);
        if (visit.hasAPitcherWithContents(goal))
            return visit.getParentPath();
        else {
            for (PitcherMove pm : visit.getPossibleMoves()) {
                PitcherConfiguration next = visit.executeMove(pm);
                if (!visited.contains(next))
                    return DFSVisit(next, goal, visited);
            }
        }
        return null;
    }

    protected static List<PitcherMove> solveDFS( PitcherConfiguration pcs, int goal, List<PitcherConfiguration> visited ) {
        return DFSVisit(pcs, goal, visited);
    }

    public ArrayList<PitcherMove> solve(PitcherConfiguration inpc, int goal) {
        return recursiveDFSSolution(inpc, goal);
    }

    public ArrayList<PitcherMove> recursiveDFSSolution( PitcherConfiguration inpc, int goal )
    {
        this.pc = inpc;
        return recursiveDFSSolution(goal);
    }

    public ArrayList<PitcherMove> recursiveDFSSolution(int goal) {
        List<PitcherConfiguration> visited = new ArrayList<>();
        List<PitcherMove> output = DFSVisit(pc, goal, visited);
        if (output != null) return new ArrayList<>(output);
        return new ArrayList<>();
    }

    public List<PitcherMove> iterativeStackDFSSolution(PitcherConfiguration inpc, int goal) {
        this.pc = inpc;
        return iterativeStackDFSSolution(goal);
    }

    public List<PitcherMove> iterativeStackDFSSolution(int goal) {
        List<String> visited = new ArrayList<>();
        visited.add(pc.toString());
        Stack<PitcherConfiguration> s = new Stack<>();
        s.push(pc);
        while (!s.peek().hasAPitcherWithContents(goal)) {
            PitcherConfiguration visit = s.pop();
            if (!visited.contains(visit.toString())) {
                visited.add(visit.toString());
                for (PitcherMove pm : visit.getPossibleMoves()) {
                    s.push(visit.executeMove(pm));
                }
            }
        }
        return s.peek().getParentPath();
    }

}
