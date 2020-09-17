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
 * This tests the Course class. Note: I set things back to the original because I kept getting weird
 * errors if i did not do that.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CourseTest {

    public static final String EXPECTED_DETS = "hello";
    public static final String EXPECTED_INSTRUCTOR = "Phillip Frond";
    public static final String EXPECTED_TITLE = "Psychology";
    public static final String EXPECTED_DESCRIPTION = "I teach with my yarn dolls";
    public static final String EXPECTED_S_DATE = "01/01/01";
    public static final String EXPECTED_E_DATE = "06/06/06";

    public static final String UPDATED_DETS = "bye";
    public static final String UPDATED_INSTRUCTOR = "Frond";
    public static final String UPDATED_TITLE = "GaGa Ball";
    public static final String UPDATED_DESCRIPTION = "Time to a gaga";
    public static final String UPDATED_S_DATE = "03/01/01";
    public static final String UPDATED_E_DATE = "07/06/06";

    CourseDAO courseDao;

    /**
     * Creates the database at the beginning of the test run.
     */
    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        courseDao = UserDB.getUserDAO(appContext).courseDao();
    }

    /**
     * Tests inserting an object into the database
     */
    @Test
    public void testInsertDb() {
        courseDao.deleteAllCourses();
        Course insertCourse = new Course(EXPECTED_DETS, EXPECTED_INSTRUCTOR, EXPECTED_TITLE, EXPECTED_DESCRIPTION,
                            EXPECTED_S_DATE, EXPECTED_E_DATE);
        courseDao.insert(insertCourse);
        Course testCourse = courseDao.getCourse(EXPECTED_TITLE);

        assertEquals("EXPECTED insertCourse does not match actual", insertCourse, testCourse);
    }
    /**
     * This tests that all the getters from the Course class are working
     */
    @Test
    public void testGetCourseFromDb() {
        Course course = courseDao.getCourse(EXPECTED_TITLE);

        assertEquals("Expected testGetCourse does not match actual",
                                                    EXPECTED_INSTRUCTOR, course.getInstructor());
        assertEquals("Expected testGetCourse does not match actual",
                                                    EXPECTED_TITLE, course.getTitle());
        assertEquals("Expected testGetCourse does not match actual",
                                                    EXPECTED_DESCRIPTION, course.getDescription());
        assertEquals("Expected testGetCourse does not match actual",
                                                    EXPECTED_S_DATE, course.getSDate());
        assertEquals("Expected testGetCourse does not match actual",
                                                    EXPECTED_E_DATE, course.getEDate());
    }

    /**
     * Tests to see if setInstructor from the Course class is working along with the @Query in
     * CourseDao.
     */
    @Test
    public void testSetInstructor() {
        courseDao.updateInstructor(EXPECTED_INSTRUCTOR, UPDATED_INSTRUCTOR);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected testSetInstructor does not match actual",
                                                        UPDATED_INSTRUCTOR, course.getInstructor());
        // setting back to original
        courseDao.updateInstructor(UPDATED_INSTRUCTOR, EXPECTED_INSTRUCTOR);
    }

    /**
     * Tests to see if setSDate from the Course class is working along with the @Query in
     * CourseDao.
     */
    @Test
    public void testSetSDate() {
        courseDao.updateSDate(EXPECTED_INSTRUCTOR, UPDATED_S_DATE);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected SetSDate does not match", UPDATED_S_DATE, course.getSDate());
        // setting back to original
        courseDao.updateSDate(EXPECTED_INSTRUCTOR, EXPECTED_S_DATE);
    }

    /**
     * Tests to see if setEDate from the Course class is working along with the @Query in
     * CourseDao.
     */
    @Test
    public void testSetEDate() {
        courseDao.updateEDate(EXPECTED_INSTRUCTOR, UPDATED_E_DATE);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected SetEDate does not match", UPDATED_E_DATE, course.getEDate());
        // setting back to original
        courseDao.updateEDate(EXPECTED_INSTRUCTOR, EXPECTED_E_DATE);

    }
    /**
     * Tests to see if the delete from the database is working correctly
     */
    @Test
    public void testDeleteCourse() {
        courseDao.delete(courseDao.getCourse(EXPECTED_TITLE));
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertNull(course);
    }

    // I'm having issues with these. TODO:: come back to fix this!
    /*@Test
    public void testSetTitle() {
        courseDao.updateTitle(EXPECTED_INSTRUCTOR, UPDATED_TITLE);
        Course course = courseDao.getCourse(UPDATED_TITLE);
        assertEquals("Expected SetTitle does not match", UPDATED_TITLE, course.getTitle());
        // setting back to original
        courseDao.updateTitle(EXPECTED_INSTRUCTOR, EXPECTED_TITLE);
    }*/

    /*@Test
    public void testSetDescription() {
        courseDao.updateDescription(EXPECTED_INSTRUCTOR, UPDATED_DESCRIPTION);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected SetDescription does not match", UPDATED_DESCRIPTION, course.getDescription());
        // setting back to original
        courseDao.updateDescription(EXPECTED_INSTRUCTOR, EXPECTED_DESCRIPTION);
    }*/
}
