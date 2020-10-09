import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class KempeChain {
    ArrayList<Course> Chain;
    public KempeChain()
    {
        Chain = new ArrayList<>();
    }
    public double Algorithm(Graph G)
    {
        double FinalPenalty = 0.0;
        double prevPenalty = Main.Penalty(G);
        Chain.clear();
        for (int o=0;o<100;o++)
        {

            Random rand = new Random();
            int randomCourseID = rand.nextInt(G.NoOfVertices);
            int slot1, slot2;
            Course first = G.listOfVertices.get(randomCourseID);
            Chain.add(G.listOfVertices.get(randomCourseID ));
            slot1 = first.slotNo;
            randomCourseID = rand.nextInt(first.courses.size());
            Course second = first.courses.get(randomCourseID );
            slot2 = second.slotNo;
            //System.out.println(first + " " + second);
            if(!first.courses.contains(second))
            {
                System.out.println("Does not Contain");
            }

            boolean visited[] = new boolean[G.NoOfVertices];

            LinkedList<Course> queue = new LinkedList();

            visited[second.CourseId - 1] = true;
            queue.add(G.listOfVertices.get(second.CourseId - 1));

            while (queue.size() != 0) {
                Course s = queue.poll();
                Chain.add(s);

                for (Course i : s.courses) {
                    if (!visited[i.CourseId - 1] && (i.slotNo == slot1 || i.slotNo == slot2)) {
                        visited[i.CourseId - 1] = true;
                        queue.add(i);
                    }
                }

            }



            for (Course i : Chain
            ) {
                if (i.slotNo == slot1)
                    G.listOfVertices.get(i.CourseId - 1).slotNo = slot2;
                else
                    G.listOfVertices.get(i.CourseId - 1).slotNo = slot1;

            }
            double Temp_penalty = Main.Penalty(G);

            if (Temp_penalty > prevPenalty) {
                for (Course i : Chain
                ) {
                       // G.listOfVertices.get(i.CourseId - 1).slotNo = i.slotNo;
                    if (i.slotNo == slot1)
                        G.listOfVertices.get(i.CourseId - 1).slotNo = slot2;
                    else
                        G.listOfVertices.get(i.CourseId - 1).slotNo = slot1;

                }
            } else {
                prevPenalty = Temp_penalty;
            }
           // System.out.println(prevPenalty + " ----> " + Temp_penalty);

            Chain.clear();

        }


        return prevPenalty;
    }
}
