package be.intecbrussel.bbjja.api;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ( value = EndPoints.EMPLOYEE_CLASS_LEVEL )
@PermitAll
public class EmployeeApi {

	private final EmployeeService employeeService;


	@Autowired
	public EmployeeApi( final EmployeeService employeeService ) {

		this.employeeService = employeeService;
	}


	@GetMapping ( EndPoints.EMPLOYEE_GET_BY_ID )
	public Optional< Employee > get( @PathVariable @NotNull final UUID id ) {

		return employeeService.get( id );
	}


	@GetMapping ( EndPoints.EMPLOYEE_GET_BY_EMAIL )
	public Optional< Employee > get( @PathVariable @Valid final String email ) {

		return employeeService.get( email );
	}


	@PostMapping ( EndPoints.EMPLOYEE_CREATE )
	public Employee create( @RequestBody @Valid final Employee entity ) {

		return employeeService.create( entity );
	}


	@PutMapping ( EndPoints.EMPLOYEE_UPDATE_BY_EXAMPLE )
	public Employee update( final Employee entity ) {

		return employeeService.update( entity );
	}


	@DeleteMapping ( EndPoints.EMPLOYEE_DELETE_BY_ID )
	public void delete( @PathVariable final UUID id ) {

		employeeService.delete( id );
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_IN_PAGES )
	public Page< Employee > list( @PathVariable final Integer page ) {

		return employeeService.list( PageRequest.of( page, 25 ) );
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_ALL )
	public List< Employee > list() {

		return employeeService.list();
	}


	@GetMapping ( EndPoints.EMPLOYEE_LIST_SORTED )
	public List< Employee > list( final Sort sort ) {

		return employeeService.list( sort );
	}


	@GetMapping ( EndPoints.EMPLOYEES_COUNT )
	public int count() {

		return employeeService.count();
	}

}
