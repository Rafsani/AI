import java.util.*;

public class Graph {
    int NoOfVertices;
    ArrayList<Course> listOfVertices;

    public Graph() {
        NoOfVertices = 0;
        listOfVertices = new ArrayList<>();
    }


    public void addEdge(int a,int b)
    {
        if( listOfVertices.get(a).courses.indexOf(listOfVertices.get(b)) == -1)
        {
            listOfVertices.get(a).courses.add(listOfVertices.get(b));
            listOfVertices.get(a).Degree++;
        }

        if( listOfVertices.get(b).courses.indexOf(listOfVertices.get(a)) == -1)
        {
            listOfVertices.get(b).courses.add(listOfVertices.get(a));
            listOfVertices.get(b).Degree++;
        }

    }
    public void addVertex(Course C)
    {
        listOfVertices.add(C);
        NoOfVertices++;
    }

    public void printGraph()
    {
       for(int d =0; d< listOfVertices.size(); d++)
       {
           Course crs = listOfVertices.get(d);
           System.out.print(crs.CourseId+ " Deg = "+ crs.Degree +" ->  ");
           for (Course id: crs.courses
                ) {
               System.out.print(id.CourseId + " ");
           }
           System.out.println();
       }
    }
}

