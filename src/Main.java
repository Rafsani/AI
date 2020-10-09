import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static double penaltyCalc(Course c1,Course c2)
    {
        int diff = Math.abs(c1.slotNo-c2.slotNo);
        switch(diff) {
            case 1:
                return 16;
            case 2:
                return 8;
            case 3:
                return 4;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                // code block
                return 0;
        }


    }

    public static double Penalty(Graph G)
    {
        int no_of_students = 0;
        double TotalPenalty = 0;
        try {
            Scanner sc1 = new Scanner(new File("input1.txt"));
            while (sc1.hasNext()) {
                no_of_students++;
                String str = sc1.nextLine();
                if(!str.equals("")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str);
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    while(stringTokenizer.hasMoreTokens()) {
                        list.add(new Integer(stringTokenizer.nextToken()));
                    }
                    for(int i = 0; i<list.size(); i++)
                    {
                        for(int j=i+1; j<list.size(); j++)
                        {
                            //G.addEdge(list.get(i)-1, list.get(j)-1);

                            TotalPenalty=TotalPenalty + penaltyCalc(G.listOfVertices.get(list.get(i)-1),G.listOfVertices.get(list.get(j)-1));
                        }
                    }

                }else{
                    break;
                }
            }
            sc1.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return TotalPenalty/no_of_students;
    }



    public static void LargestDegreeColoring(Graph G)
    {
        int Colours[] = new int[G.listOfVertices.size()];

        // Initialize all vertices as unassigned
        Arrays.fill(Colours, -1);

        // Assign the first color to first vertex
     //   Colours[0] = 0;

        // A temporary array to store the Colours_available colors. False
        // value of Colours_available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        PriorityQueue<Course>  pq = new PriorityQueue<>();
        for (int i=0; i<G.listOfVertices.size(); i++)
        {
            pq.add(G.listOfVertices.get(i));
        }

        boolean Colours_available[] = new boolean[G.listOfVertices.size()];

        // Initially, all colors are Colours_available
        Arrays.fill(Colours_available, true);
        Course first = pq.poll();
        Colours[first.CourseId-1] = 0;

        // Assign colors to remaining V-1 vertices
        while(!pq.isEmpty())
        {
            Course u = pq.poll();
            // Process all adjacent vertices and flag their colors
            // as unColours_available
            Iterator<Course> it = G.listOfVertices.get(u.CourseId-1).courses.iterator() ;
            while (it.hasNext())
            {
                Course i = it.next();
                if (Colours[i.CourseId-1] != -1)
                    Colours_available[Colours[i.CourseId-1]] = false;
            }

            // Find the first Colours_available color
            int cr;
            for (cr = 0; cr < G.listOfVertices.size(); cr++){
                if (Colours_available[cr])
                    break;
            }

            Colours[u.CourseId-1] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(Colours_available, true);
        }

        // print the Colours
        int slots = -1;
        for (int u = 0; u < G.listOfVertices.size(); u++){
            System.out.println("Course " + (u+1) + " ---> Slot "
                    + Colours[u]);
            G.listOfVertices.get(u).slotNo = Colours[u];
            if(slots<Colours[u])
            {
                slots = Colours[u];
            }
        }
        System.out.println("Total No of slots = "+ (slots+1));
    }


public static void greedyColoring(Graph G)
    {
        int Colours[] = new int[G.listOfVertices.size()];

        // Initialize all vertices as unassigned 
        Arrays.fill(Colours, -1);

        // Assign the first color to first vertex 
        Colours[0] = 0;

        // A temporary array to store the Colours_available colors. False 
        // value of Colours_available[cr] would mean that the color cr is 
        // assigned to one of its adjacent vertices 
        boolean Colours_available[] = new boolean[G.listOfVertices.size()];

        // Initially, all colors are Colours_available 
        Arrays.fill(Colours_available, true);

        // Assign colors to remaining V-1 vertices 
        for (int u = 1; u < G.listOfVertices.size(); u++)
        {
            // Process all adjacent vertices and flag their colors 
            // as unColours_available 
            Iterator<Course> it = G.listOfVertices.get(u).courses.iterator() ;
            while (it.hasNext())
            {
                Course i = it.next();
                if (Colours[i.CourseId-1] != -1)
                    Colours_available[Colours[i.CourseId-1]] = false;
            }

            // Find the first Colours_available color 
            int cr;
            for (cr = 0; cr < G.listOfVertices.size(); cr++){
                if (Colours_available[cr])
                    break;
            }

            Colours[u] = cr; // Assign the found color 

            // Reset the values back to true for the next iteration 
            Arrays.fill(Colours_available, true);
        }

        // print the Colours
        int slots = -1;
        for (int u = 0; u < G.listOfVertices.size(); u++){
            System.out.println("Course " + (u+1) + " ---> Slot "
                    + Colours[u]);
            G.listOfVertices.get(u).slotNo = Colours[u];
            if(slots<Colours[u])
            {
                slots = Colours[u];
            }
        }
        System.out.println("Total No of slots = "+ (slots+1));
    }


    public static void main(String[] args) {
        Graph G=new Graph();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                if(!str.equals("")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str);
                    int i= 0,a = 0,b=0;
                   while(stringTokenizer.hasMoreTokens()) {
                       if(i==0) {
                           a = new Integer(stringTokenizer.nextToken());
                           i++;
                       }
                        else{
                           b = new Integer(stringTokenizer.nextToken());
                       }
                    }
                   Course c = new Course(a,b);
                    G.addVertex(c);
                    //System.out.println(c);
                }else{
                    break;
                }
            }
            sc.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        try {
            Scanner sc1 = new Scanner(new File("input1.txt"));
            while (sc1.hasNext()) {
                String str = sc1.nextLine();
                if(!str.equals("")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str);
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    while(stringTokenizer.hasMoreTokens()) {
                        list.add(new Integer(stringTokenizer.nextToken()));
                    }
                    for(int i = 0; i<list.size(); i++)
                    {
                        for(int j=0; j<list.size(); j++)
                        {
                            if(i!=j)
                            {
                                G.addEdge(list.get(i)-1, list.get(j)-1);
                            }
                        }
                    }

                }else{
                    break;
                }
            }
            sc1.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        G.printGraph();
        greedyColoring(G);
        System.out.println(Penalty(G));
        LargestDegreeColoring(G);
        System.out.println(Penalty(G));
        KempeChain K = new KempeChain();
        K.Algorithm(G);

        }



}
