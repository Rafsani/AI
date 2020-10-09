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


        for (int o=0;o<100;o++) {
            Random rand = new Random();
            int randomCourseID = rand.nextInt(G.NoOfVertices);
            int slot1, slot2;
            Course first = G.listOfVertices.get(randomCourseID -1);
            Chain.add(G.listOfVertices.get(randomCourseID -1));
            slot1 = first.slotNo;
            randomCourseID = rand.nextInt(first.courses.size());
            Course second = first.courses.get(randomCourseID -1);
            slot2 = second.slotNo;
            System.out.println(first + " " + second);


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


            double prevPenalty = Main.Penalty(G);
            for (Course i : Chain
            ) {
                if (i.slotNo == slot1)
                    G.listOfVertices.get(i.CourseId - 1).slotNo = slot2;
                else
                    G.listOfVertices.get(i.CourseId - 1).slotNo = slot1;

            }
            double currentPenalty = Main.Penalty(G);

            if (currentPenalty > prevPenalty) {
                for (Course i : Chain
                ) {
                        G.listOfVertices.get(i.CourseId - 1).slotNo = i.slotNo;

                }
            } else {
                FinalPenalty = currentPenalty;
            }
            System.out.println(prevPenalty + " ----> " + currentPenalty);

        }


        return FinalPenalty;
    }
}
