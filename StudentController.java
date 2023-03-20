


package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController
{
	@Autowired
	JdbcTemplate jtemp;
	
	
	@RequestMapping(value="/student/{rollno}",method=RequestMethod.DELETE)
	public ResponseEntity<Object>delete(@PathVariable("rollno")String rollno)
	{
        jtemp.update("delete from student1 where rollno=?",rollno);
         return new ResponseEntity<>("student is deleted succesfully",HttpStatus.OK);
}
	@RequestMapping(value="/student/{rollno}",method=RequestMethod.PUT)
	public ResponseEntity<Object>UpdateProduct(@PathVariable("rollno")String rollno,@RequestBody Student student)
	{
		jtemp.update("update student1 set fname=?,lname=?,mark=? where rollno=?",student.getFname(),student.getLname(),student.getMarks(), rollno);
		return new ResponseEntity<>("student is updated succesfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public ResponseEntity<Object>CreateProduct(@RequestBody Student student)
	{
		jtemp.update("insert into student1 values(?,?,?,?)",student.getRollno(),student.getFname(),student.getLname(),student.getMarks());
		return new ResponseEntity<> ("student is inserted succesfully",HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/student")
	public List<Student> getstudent()
	{
		List<Student> l=new ArrayList<>();
		
		jtemp.query("select * from student1", new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				Student s=new Student();
				s.setRollno(rs.getString(1));
				s.setFname(rs.getString(2));
				s.setLname(rs.getString(3));
				s.setMarks(rs.getString(4));
				l.add(s);
				return s;
			
				
			}
			
		});
		return l;
		
	}
	}
	
