package application;

import application.models.Product;
import application.models.Review;
import application.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreeOfSeparationTest {
    private ArrayList<User> users;
    private static final int NUM_USERS = 9;

    @BeforeEach
    public void setup() {
        users = new ArrayList<>();
        // Instantiate Users
        for(int i = 0; i < NUM_USERS; i++) {
            users.add(new User("user"+i, "pass"+i));
        }
        // creating user relation as shown below ( eg. user 0 is followed by user1,2,3)
        //     0
        //   / | \
        //  1  2  3
        //  |  |
        //  4  5
        //  |
        //  6
        //  |
        //  7
        users.get(0).addFollower(users.get(1));
        users.get(0).addFollower(users.get(2));
        users.get(0).addFollower(users.get(3));
        users.get(1).addFollower(users.get(4));
        users.get(2).addFollower(users.get(5));
        users.get(4).addFollower(users.get(6));
        users.get(6).addFollower(users.get(7));

    }

    @Test
    public void testCalculateSingleJaccardDistance() {
        // DOF = 1 for users[0] with users[1] and users[2] and users[3]
        assertEquals(1, users.get(0).getDegreesOfSeparation(users.get(1)));
        assertEquals(1, users.get(0).getDegreesOfSeparation(users.get(2)));
        assertEquals(1, users.get(0).getDegreesOfSeparation(users.get(3)));

        // DOF = 2 for users[0] with users[4] and users[5]
        assertEquals(2, users.get(0).getDegreesOfSeparation(users.get(4)));
        assertEquals(2, users.get(0).getDegreesOfSeparation(users.get(5)));

        // DOF = 3 for users[0] with users[6]
        assertEquals(3, users.get(0).getDegreesOfSeparation(users.get(6)));

        // DOF = 4 for users[0] with users[7]
        assertEquals(4, users.get(0).getDegreesOfSeparation(users.get(7)));
    }

    @Test
    public void testDegreeOfSeparationSymmetry() {
        // Degree of Separation between any two users should be symmetrical
        int degree1 = users.get(0).getDegreesOfSeparation(users.get(1));
        int degree2 = users.get(1).getDegreesOfSeparation(users.get(0));
        assertEquals(degree1, degree2);
    }

    @Test
    public void testOutOfBoundDegreeOfSeparation() {
        // Degree of Separation between any two users should be symmetrical
        int degree1 = users.get(0).getDegreesOfSeparation(users.get(8));
        assertEquals(-1, degree1);
    }
}
