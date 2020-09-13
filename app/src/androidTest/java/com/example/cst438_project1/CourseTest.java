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
public class CourseTest {
    public static final String EXPECTED_INSTRUCTOR = "Phillip Frond";
    public static final String EXPECTED_TITLE = "Psychology";
    public static final String EXPECTED_DESCRIPTION = "I teach with my yarn dolls";
    public static final String EXPECTED_S_DATE = "01/01/01";
    public static final String EXPECTED_E_DATE = "06/06/06";

    public static final String UPDATED_INSTRUCTOR = "Frond";
    public static final String UPDATED_TITLE = "GaGa Ball";
    public static final String UPDATED_DESCRIPTION = "Time to a gaga";
    public static final String UPDATED_S_DATE = "03/01/01";
    public static final String UPDATED_E_DATE = "07/06/06";


    CourseDAO courseDao;

    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        courseDao = UserDB.getUserDAO(appContext).courseDao();
    }

    @Test
    public void testInsertDb() {
        courseDao.deleteAllCourses();
        Course insertCourse = new Course(EXPECTED_INSTRUCTOR, EXPECTED_TITLE, EXPECTED_DESCRIPTION,
                            EXPECTED_S_DATE, EXPECTED_E_DATE);
        courseDao.insert(insertCourse);
        Course testCourse = courseDao.getCourse(EXPECTED_TITLE);

        assertEquals("EXPECTED insertCourse does not match actual", insertCourse, testCourse);
    }

    @Test
    public void testGetCourseFromDb() {
        Course course = courseDao.getCourse(EXPECTED_TITLE);

        assertEquals("Expected testGetCourse does not match actual", EXPECTED_INSTRUCTOR, course.getInstructor());
        assertEquals("Expected testGetCourse does not match actual", EXPECTED_TITLE, course.getTitle());
        assertEquals("Expected testGetCourse does not match actual", EXPECTED_DESCRIPTION, course.getDescription());
        assertEquals("Expected testGetCourse does not match actual", EXPECTED_S_DATE, course.getSDate());
        assertEquals("Expected testGetCourse does not match actual", EXPECTED_E_DATE, course.getEDate());
    }

    @Test
    public void testInstructor() {
        courseDao.updateInstructor(EXPECTED_INSTRUCTOR, UPDATED_INSTRUCTOR);

        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected testSetInstructor does not match actual", UPDATED_INSTRUCTOR, course.getInstructor());
        // setting back to original
        courseDao.updateInstructor(UPDATED_INSTRUCTOR, EXPECTED_INSTRUCTOR);
    }
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

    @Test
    public void testSetSDate() {
        courseDao.updateSDate(EXPECTED_INSTRUCTOR, UPDATED_S_DATE);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected SetSDate does not match", UPDATED_S_DATE, course.getSDate());
        // setting back to original
        courseDao.updateSDate(EXPECTED_INSTRUCTOR, EXPECTED_S_DATE);
    }

    @Test
    public void testSetEDate() {
        courseDao.updateEDate(EXPECTED_INSTRUCTOR, UPDATED_E_DATE);
        Course course = courseDao.getCourse(EXPECTED_TITLE);
        assertEquals("Expected SetEDate does not match", UPDATED_E_DATE, course.getEDate());
        // setting back to original
        courseDao.updateEDate(EXPECTED_INSTRUCTOR, EXPECTED_E_DATE);

    }
}
