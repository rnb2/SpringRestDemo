package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.dao.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    //private final EmployeeDao employeeDao;
    private final EmployeeRepository employeeRepository;

    /** Example 1
     * self join EmployeeService
     * need to achieve work @Transactional(propagation = Propagation.REQUIRES_NEW) in callRemoteService()
     * also one important point in the constructor injection it is a @Lazy annotation, used to avoid exception 'recursion' when bean initialised
     *
     */
    //private final EmployeeService employeeService;

    /** Example 2 to achieve the same (self join) via bean factory
     *
     */
    //private final BeanFactory beanFactory;

    /**
     *  Example 3 to achieve the same (self join) via bean factory but with supplier interface
     */
    private final Supplier<EmployeeService> employeeServiceSupplier;

    //Example #1: @Transactional(propagation = Propagation.REQUIRES_NEW) via selfJoin and @Lazy annotation
    //@Autowired
    //public EmployeeServiceImpl(EmployeeRepository employeeRepository,
    //    @Lazy
    //     EmployeeService employeeService) {
    //  this.employeeRepository = employeeRepository;
    // this.employeeService = employeeService;
    //}

    //Example #2: @Transactional(propagation = Propagation.REQUIRES_NEW) via beanFactory
    /*@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BeanFactory beanFactory)
    {
        this.employeeRepository = employeeRepository;
        this.beanFactory = beanFactory;
    }*/

    //Example #3: @Transactional(propagation = Propagation.REQUIRES_NEW) via beanFactory and supplier
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BeanFactory beanFactory)
    {
        this.employeeRepository = employeeRepository;
        this.employeeServiceSupplier = () -> beanFactory.getBean(EmployeeService.class);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    //@Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public List<Employee> findByName(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee update(int id, Employee employee)
    {
        log.debug("Request to save Employee : {}", employee);

        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent())
        {
            Employee employeeFound = employeeOptional.get();
            employeeFound.setEmail(employee.getEmail());
            employeeFound.setFirstName(employee.getFirstName());
            employeeFound.setLastName(employee.getLastName());

            //Example #1 via selfJoin and Lazy
            //employeeService.callRemoteService();

            //Example #2 via beanFactory
            //beanFactory.getBean(EmployeeService.class).callRemoteService();

            // Example #3 via beanFactory and suppler
            employeeServiceSupplier.get().callRemoteService();

            log.debug("Update Employee : {}, and callRemoteService end.", employeeFound);
            return employeeRepository.save(employeeFound);
        }

        return employee;
    }

    @Override
   // @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    /*
        так же как вариант(самый лучший) для Example #4 (see free previous above) можно данный сервис(EmployeeService) разделить
        на два и вынести метод callRemoteService в сервисВ. Тогда спринг сам создаст из сервиса прокси и транзакция будет новой
        и Propagation.REQUIRES_NEW сработает
     */
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void callRemoteService()
  {
    System.out.println("callRemoteService");
  }
}
