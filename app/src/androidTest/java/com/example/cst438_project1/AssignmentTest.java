package com.example.cst438_project1;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AssignmentTest {
    private static final String EXPECTED_DETS = "Create a hello world project";
    private static final int EXPECTED_MAX_SCORE = 50;
    private static final int EXPECTED_EARNED_SCORE = 40;
    private static final String EXPECTED_ASSIGNED_DATE = "01/01/01";
    private static final String EXPECTED_DUE_DATE = "02/02/02";
    private static final int EXPECTED_CATEGORY_ID = 1;
    private static final int EXPECTED_COURSE_ID = 1;

    private static final String UPDATED_DETS = "Create a hello world project in java";
    private static final int UPDATED_MAX_SCORE = 100;
    private static final int UPDATED_EARNED_SCORE = 100;
    private static final String UPDATED_ASSIGNED_DATE = "02/02/02";
    private static final String UPDATED_DUE_DATE = "03/03/03";
    private static final int UPDATED_CATEGORY_ID = 2;
    private static final int UPDATED_COURSE_ID = 2;

    private AssignmentDAO assignmentDao;

    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assignmentDao = UserDB.getUserDAO(appContext).assignmentDao();
    }

    @Test
    public void testInsertAssignment() {
        assignmentDao.deleteAllAssignment();
        Assignment insertAssignment = new Assignment(EXPECTED_DETS, EXPECTED_MAX_SCORE, EXPECTED_EARNED_SCORE);
        assignmentDao.insert(insertAssignment);
        Assignment testAssignment = assignmentDao.getAssignmentByDetails(EXPECTED_DETS);

        assertEquals("Expected insertAssignment does not match actual",
                        insertAssignment, testAssignment);
    }
    @Test
    public void testGetAssignmentFromDb() {
        Assignment assignment = assignmentDao.getAssignmentByDetails(EXPECTED_DETS);

        assertEquals("Expected getAssignement does not match actual", EXPECTED_DETS, assignment.getDets());
        assertEquals("Expected getAssignement does not match actual", EXPECTED_MAX_SCORE, assignment.getMaxScore());
        assertEquals("Expected getAssignement does not match actual", EXPECTED_EARNED_SCORE, assignment.getEarnedScore());

    }
    @Test
    public void testSetMaxScore() {
        assignmentDao.updateMaxScore(EXPECTED_DETS, UPDATED_MAX_SCORE);
        Assignment assignment = assignmentDao.getAssignmentByDetails(EXPECTED_DETS);
        assertEquals("Expected setMaxScore does not match actual", UPDATED_MAX_SCORE, assignment.getMaxScore());
        // setting back to original
        assignmentDao.updateMaxScore(EXPECTED_DETS, EXPECTED_MAX_SCORE);
    }

    @Test
    public void testSetEarnedScore() {
        assignmentDao.updateEarnedScore(EXPECTED_DETS, UPDATED_EARNED_SCORE);
        Assignment assignment = assignmentDao.getAssignmentByDetails(EXPECTED_DETS);
        assertEquals("Expected setEarnedScore does not match actual", UPDATED_EARNED_SCORE, assignment.getEarnedScore());
        // setting back to original
        assignmentDao.updateEarnedScore(EXPECTED_DETS, EXPECTED_EARNED_SCORE);
    }

    @Test
    public void testSetDetails() {
        assignmentDao.updateDetails(EXPECTED_DETS, UPDATED_DETS);
        Assignment assignment = assignmentDao.getAssignmentByDetails(UPDATED_DETS);
        assertEquals("Expected setDetails does not match actual", UPDATED_DETS, assignment.getDets());
        // setting back to original
        assignmentDao.updateDetails(UPDATED_DETS, EXPECTED_DETS);
    }

    @Test
    public void testDeleteAssignment() {
        assignmentDao.delete(assignmentDao.getAssignmentByDetails(EXPECTED_DETS));
        Assignment assignment = assignmentDao.getAssignmentByDetails(EXPECTED_DETS);
        assertNull(assignment);
    }

}
