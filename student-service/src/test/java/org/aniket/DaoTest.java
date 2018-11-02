package org.aniket;

import static org.junit.Assert.*;

import java.util.List;

import org.aniket.dao.StudentDAO;
import org.aniket.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentDAO.class)
public class DaoTest
{
	@Autowired
	private StudentDAO studentDAO;
	
	@Test
    public void testGetBySchoolId() {
		List<Student> students=studentDAO.getBySchoolId(1);
		assertNotNull(students);
		assertEquals(students.size(),2);
    }

}
