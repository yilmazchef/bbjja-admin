package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.EmployeeResponse;
import be.intecbrussel.bbjja.data.dto.NewEmployeeRequest;
import be.intecbrussel.bbjja.data.dto.UpdateEmployeeRequest;
import be.intecbrussel.bbjja.data.entity.Employee;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface EmployeeMapper {

	Employee newEmployeeRequestToEmployee( NewEmployeeRequest newEmployeeRequest );

	NewEmployeeRequest employeeToNewEmployeeRequest( Employee employee );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateEmployeeFromNewEmployeeRequest( NewEmployeeRequest newEmployeeRequest, @MappingTarget Employee employee );

	Employee updateEmployeeRequestToEmployee( UpdateEmployeeRequest updateEmployeeRequest );

	UpdateEmployeeRequest employeeToUpdateEmployeeRequest( Employee employee );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateEmployeeFromUpdateEmployeeRequest( UpdateEmployeeRequest updateEmployeeRequest, @MappingTarget Employee employee );

	Employee employeeResponseToEmployee( EmployeeResponse employeeResponse );

	EmployeeResponse employeeToEmployeeResponse( Employee employee );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateEmployeeFromEmployeeResponse( EmployeeResponse employeeResponse, @MappingTarget Employee employee );

}
