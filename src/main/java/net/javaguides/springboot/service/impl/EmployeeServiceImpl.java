package net.javaguides.springboot.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourseNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	                
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		//Optional<Employee> employee = employeeRepository.findById(id);
		//if(employee.isPresent()) {
			//return employee.get();
		//}else {
			//throw new ResourseNotFoundException("Employee", "Id", id);
		//}
		return employeeRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Employee", "Id", id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourseNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existingEmployee);
		return null;
	}


	@Override
	public void deleteEmployee(long id) {
		
		employeeRepository
		.findById(id).orElseThrow(() -> new ResourseNotFoundException("Employee", "Id", id));
	employeeRepository.deleteById(id);


	}
	
	
	
	
	
}
