package unrc.dose;

import static org.junit.Assert.assertEquals;

import org.javalite.activejdbc.Base;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChallengeStatTest {

    @BeforeClass
	public static void before(){
        if (!Base.hasConnection()) {
            Base.open();
            Base.openTransaction();
        }
    }

    @AfterClass
	public static void after(){
        if (Base.hasConnection()) {
            Base.rollbackTransaction();
            Base.close();
        }
	}

    /**
    * creates a new ChallengeStat record on the data base.
    * @result ChallengeStat c=("challenge_id"= 1, "average_score"= 0.0, "solved_count"= 0) record sucessfully created.
    */
    @Test
    public void newChallengeStatTest() {

        ChallengeStat.newChallengeStat(1);
        Base.commitTransaction();
        ChallengeStat c = ChallengeStat.findFirst("challenge_id = ?", 1);

        assertEquals(1, c.get("challenge_id"));
        assertEquals((float) 0.0, c.get("average_score"));
        assertEquals(0, c.get("solved_count"));
        
    }

    /**
    * Updates the attribute "average_score" of a ChallengeStat record, and increments the "solved_count".
    * @result "average_score" = 6.0, "solved_count" = 2
    */
    /*@Test
    public void updateAverageScoreTest() {
              
        User u= User.findFirst("username = ?", "carlos");
        int userId= u.getInteger("id");
        Proposition p1= Proposition.newProposition(userId, 1);
        p1.setDistance(3);
        
        ChallengeStat.newChallengeStat(1);
        
        ChallengeStat cs = ChallengeStat.findFirst("challenge_id = ?", 1);
        final int pId= p1.getInteger("id");

        System.out.println(cs.get("average_score"));
        ChallengeStat.updateAverageScore(pId);
        System.out.println(cs.get("average_score"));
        assertEquals(7.0, cs.get("average_score"));
        assertEquals(1, cs.get("solved_count"));
        

    }*/

    @Test
    public void getChallengeStatTest() {
        ChallengeStat.newChallengeStat(1);
        Base.commitTransaction();

        ChallengeStat c = ChallengeStat.findFirst("challenge_id = ?", 1);
        ChallengeStat result = ChallengeStat.getChallengeStat(1);

        assertEquals(c.getInteger("id"), result.getInteger("id"));
        assertEquals(c.get("challenge_id"), result.get("challenge_id"));
        assertEquals(c.get("average_score"), result.get("average_score"));
        assertEquals(c.get("solved_count"), result.get("solved_count"));
    }


}
