
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FamilyForest {

    //a map from the name to a node in the system
    //we keep tracing the parents of each node to find the representative of the family
    Map<String, PersonNode> allPersonsMap = new HashMap<String, PersonNode>();

    static class PersonNode {
        PersonNode(String name, String p, int r) { personName = name; parent=p; rank=r;};

        String personName;
        String parent;
        int rank; //height of the family tree from parent
    }

    //make family creates a new family with the new person name as the representative of the family
    //rank(height) of the family is 0 as the family is just of the representative and no one below him/her
    //this family(represented by the new guy) is added to the family forest
    public void make_family(String s) {
        PersonNode newPerson =new PersonNode(s, s, 0);
        allPersonsMap.put(s, newPerson);
    }


    //we unite the family based on the representatives
    //while two families join, we make the bigger famiy(with higher rank) the parent of the other
    //we do this to reduce the number of update operations for the member of the 2nd family that gets merged into first
    //time complexity -> O(1)
    public String union(String s, String t) {
        String newRep = new String();
        String sRepName = find(s);
        String tRepName = find(t);

        PersonNode sRepresentative = allPersonsMap.get(sRepName);
        PersonNode tRepresentative = allPersonsMap.get(tRepName);

        if(sRepresentative.rank > tRepresentative.rank){
            tRepresentative.parent = sRepName;
            tRepresentative.rank = tRepresentative.rank + sRepresentative.rank;
            newRep = sRepName;

        } else if (tRepresentative.rank > sRepresentative.rank) {
            sRepresentative.parent = tRepName;
            sRepresentative.rank = sRepresentative.rank + tRepresentative.rank;
            newRep = tRepName;

        } else if (sRepresentative.rank == tRepresentative.rank) {
            tRepresentative.parent = sRepName;
            //since both are of same height and one get merged to other, rank(height) gets increased by 1
            sRepresentative.rank = sRepresentative.rank + 1;
            newRep = sRepName;
        }
        return newRep;
    }

    //find the representative of the person's family
    //keep tracing parents to reach the person(whose parent is the same person:self loop)
    //time complexity-> O(1) amortized
    public String find(String s) {
        PersonNode searchedPerson = allPersonsMap.get(s);

        //base condition..return representative(person who is their own parent)
        if(searchedPerson.personName == searchedPerson.parent) return searchedPerson.personName;

        //path compression -> route each member of family recursively to the representative of the family to reduce the time complexity
        searchedPerson.parent = find(searchedPerson.parent);
        return searchedPerson.parent;

    }
}
