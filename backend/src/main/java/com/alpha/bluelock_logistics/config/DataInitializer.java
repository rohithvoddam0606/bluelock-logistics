package com.alpha.bluelock_logistics.config;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alpha.bluelock_logistics.entity.*;
import com.alpha.bluelock_logistics.repository.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private LoadingRepository loadingRepository;
    @Autowired
    private UnloadingRepository unloadingRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if admin user already exists to prevent duplicates
        User existingAdmin = userRepository.findByEmail("admin@bluelock.com");
        if (existingAdmin == null) {
            // Create Users
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@bluelock.com");
            admin.setPassword("admin123");
            admin.setRole("Admin");
            userRepository.save(admin);

            User customer = new User();
            customer.setName("John Doe");
            customer.setEmail("customer@bluelock.com");
            customer.setPassword("customer123");
            customer.setRole("Customer");
            userRepository.save(customer);

            System.out.println("✅ Default users created!");
            System.out.println("Admin: admin@bluelock.com / admin123");
            System.out.println("Customer: customer@bluelock.com / customer123");
        }

        if (carrierRepository.count() == 0) {
            // Create Carriers
            Carrier carrier1 = new Carrier();
            carrier1.setId(1);
            carrier1.setName("FastShip Logistics");
            carrier1.setEmail("contact@fastship.com");
            carrier1.setContact(9876543210L);
            carrierRepository.save(carrier1);

            Carrier carrier2 = new Carrier();
            carrier2.setId(2);
            carrier2.setName("QuickMove Transport");
            carrier2.setEmail("info@quickmove.com");
            carrier2.setContact(9876543211L);
            carrierRepository.save(carrier2);

            Carrier carrier3 = new Carrier();
            carrier3.setId(3);
            carrier3.setName("SafeCargo Express");
            carrier3.setEmail("support@safecargo.com");
            carrier3.setContact(9876543212L);
            carrierRepository.save(carrier3);

            System.out.println("✅ Carriers created!");
        }

        if (addressRepository.count() == 0) {
            // Create Addresses
            Address addr1 = new Address();
            addr1.setId(1);
            addr1.setStreet("123 Main Street");
            addr1.setCity("New York");
            addr1.setPincode(10001);
            addr1.setState("New York");
            addressRepository.save(addr1);

            Address addr2 = new Address();
            addr2.setId(2);
            addr2.setStreet("456 Oak Avenue");
            addr2.setCity("Los Angeles");
            addr2.setPincode(90001);
            addr2.setState("California");
            addressRepository.save(addr2);

            Address addr3 = new Address();
            addr3.setId(3);
            addr3.setStreet("789 Pine Road");
            addr3.setCity("Chicago");
            addr3.setPincode(60601);
            addr3.setState("Illinois");
            addressRepository.save(addr3);

            Address addr4 = new Address();
            addr4.setId(4);
            addr4.setStreet("321 Elm Street");
            addr4.setCity("Houston");
            addr4.setPincode(77001);
            addr4.setState("Texas");
            addressRepository.save(addr4);

            Address addr5 = new Address();
            addr5.setId(5);
            addr5.setStreet("654 Maple Drive");
            addr5.setCity("Phoenix");
            addr5.setPincode(85001);
            addr5.setState("Arizona");
            addressRepository.save(addr5);

            System.out.println("✅ Addresses created!");
        }

        if (truckRepository.count() == 0) {
            Carrier carrier1 = carrierRepository.findById(1).orElse(null);
            Carrier carrier2 = carrierRepository.findById(2).orElse(null);
            Carrier carrier3 = carrierRepository.findById(3).orElse(null);
            Address addr1 = addressRepository.findById(1).orElse(null);
            Address addr2 = addressRepository.findById(2).orElse(null);
            Address addr3 = addressRepository.findById(3).orElse(null);

            // Create Trucks
            Truck truck1 = new Truck();
            truck1.setId(1);
            truck1.setName("Truck Alpha");
            truck1.setNumber(1001);
            truck1.setCapacity(5000);
            truck1.setStatus("Available");
            truck1.setCarrier(carrier1);
            truck1.setCurrentLocation(addr1);
            truckRepository.save(truck1);

            Truck truck2 = new Truck();
            truck2.setId(2);
            truck2.setName("Truck Beta");
            truck2.setNumber(1002);
            truck2.setCapacity(7500);
            truck2.setStatus("In Transit");
            truck2.setCarrier(carrier1);
            truck2.setCurrentLocation(addr2);
            truckRepository.save(truck2);

            Truck truck3 = new Truck();
            truck3.setId(3);
            truck3.setName("Truck Gamma");
            truck3.setNumber(1003);
            truck3.setCapacity(10000);
            truck3.setStatus("Available");
            truck3.setCarrier(carrier2);
            truck3.setCurrentLocation(addr3);
            truckRepository.save(truck3);

            Truck truck4 = new Truck();
            truck4.setId(4);
            truck4.setName("Truck Delta");
            truck4.setNumber(1004);
            truck4.setCapacity(6000);
            truck4.setStatus("Maintenance");
            truck4.setCarrier(carrier2);
            truck4.setCurrentLocation(addr1);
            truckRepository.save(truck4);

            Truck truck5 = new Truck();
            truck5.setId(5);
            truck5.setName("Truck Epsilon");
            truck5.setNumber(1005);
            truck5.setCapacity(8000);
            truck5.setStatus("In Transit");
            truck5.setCarrier(carrier3);
            truck5.setCurrentLocation(addr2);
            truckRepository.save(truck5);

            System.out.println("✅ Trucks created!");
        }

        if (driverRepository.count() == 0) {
            Carrier carrier1 = carrierRepository.findById(1).orElse(null);
            Carrier carrier2 = carrierRepository.findById(2).orElse(null);
            Carrier carrier3 = carrierRepository.findById(3).orElse(null);
            Truck truck1 = truckRepository.findById(1).orElse(null);
            Truck truck2 = truckRepository.findById(2).orElse(null);
            Truck truck3 = truckRepository.findById(3).orElse(null);
            Truck truck5 = truckRepository.findById(5).orElse(null);

            // Create Drivers
            Driver driver1 = new Driver();
            driver1.setId(1);
            driver1.setName("Michael Johnson");
            driver1.setContact(9123456780L);
            driver1.setCarrier(carrier1);
            driver1.setTruck(truck1);
            driverRepository.save(driver1);

            Driver driver2 = new Driver();
            driver2.setId(2);
            driver2.setName("Sarah Williams");
            driver2.setContact(9123456781L);
            driver2.setCarrier(carrier1);
            driver2.setTruck(truck2);
            driverRepository.save(driver2);

            Driver driver3 = new Driver();
            driver3.setId(3);
            driver3.setName("David Brown");
            driver3.setContact(9123456782L);
            driver3.setCarrier(carrier2);
            driver3.setTruck(truck3);
            driverRepository.save(driver3);

            Driver driver4 = new Driver();
            driver4.setId(4);
            driver4.setName("Emily Davis");
            driver4.setContact(9123456783L);
            driver4.setCarrier(carrier2);
            driver4.setTruck(null);
            driverRepository.save(driver4);

            Driver driver5 = new Driver();
            driver5.setId(5);
            driver5.setName("James Wilson");
            driver5.setContact(9123456784L);
            driver5.setCarrier(carrier3);
            driver5.setTruck(truck5);
            driverRepository.save(driver5);

            System.out.println("✅ Drivers created!");
        }

        if (cargoRepository.count() == 0) {
            // Create Cargo
            Cargo cargo1 = new Cargo();
            cargo1.setId(1);
            cargo1.setName("Electronics");
            cargo1.setDescription("Laptops and mobile devices");
            cargo1.setWeight(500);
            cargo1.setCount(100);
            cargoRepository.save(cargo1);

            Cargo cargo2 = new Cargo();
            cargo2.setId(2);
            cargo2.setName("Furniture");
            cargo2.setDescription("Office chairs and desks");
            cargo2.setWeight(2000);
            cargo2.setCount(50);
            cargoRepository.save(cargo2);

            Cargo cargo3 = new Cargo();
            cargo3.setId(3);
            cargo3.setName("Textiles");
            cargo3.setDescription("Cotton fabrics and garments");
            cargo3.setWeight(1500);
            cargo3.setCount(200);
            cargoRepository.save(cargo3);

            Cargo cargo4 = new Cargo();
            cargo4.setId(4);
            cargo4.setName("Food Products");
            cargo4.setDescription("Packaged snacks and beverages");
            cargo4.setWeight(3000);
            cargo4.setCount(500);
            cargoRepository.save(cargo4);

            Cargo cargo5 = new Cargo();
            cargo5.setId(5);
            cargo5.setName("Medical Supplies");
            cargo5.setDescription("Surgical equipment and medicines");
            cargo5.setWeight(800);
            cargo5.setCount(150);
            cargoRepository.save(cargo5);

            System.out.println("✅ Cargo created!");
        }

        if (loadingRepository.count() == 0) {
            Address addr1 = addressRepository.findById(1).orElse(null);
            Address addr2 = addressRepository.findById(2).orElse(null);
            Address addr3 = addressRepository.findById(3).orElse(null);

            // Create Loading schedules
            Loading loading1 = new Loading();
            loading1.setId(1);
            loading1.setDate(LocalDate.now().minusDays(2));
            loading1.setTime(LocalTime.of(9, 0));
            loading1.setAddress(addr1);
            loadingRepository.save(loading1);

            Loading loading2 = new Loading();
            loading2.setId(2);
            loading2.setDate(LocalDate.now().minusDays(1));
            loading2.setTime(LocalTime.of(10, 30));
            loading2.setAddress(addr2);
            loadingRepository.save(loading2);

            Loading loading3 = new Loading();
            loading3.setId(3);
            loading3.setDate(LocalDate.now());
            loading3.setTime(LocalTime.of(14, 0));
            loading3.setAddress(addr3);
            loadingRepository.save(loading3);

            System.out.println("✅ Loading schedules created!");
        }

        if (unloadingRepository.count() == 0) {
            Address addr2 = addressRepository.findById(2).orElse(null);
            Address addr4 = addressRepository.findById(4).orElse(null);
            Address addr5 = addressRepository.findById(5).orElse(null);

            // Create Unloading schedules
            Unloading unloading1 = new Unloading();
            unloading1.setId(1);
            unloading1.setDate(LocalDate.now().plusDays(1));
            unloading1.setTime(LocalTime.of(15, 0));
            unloading1.setAddress(addr2);
            unloadingRepository.save(unloading1);

            Unloading unloading2 = new Unloading();
            unloading2.setId(2);
            unloading2.setDate(LocalDate.now().plusDays(2));
            unloading2.setTime(LocalTime.of(11, 30));
            unloading2.setAddress(addr4);
            unloadingRepository.save(unloading2);

            Unloading unloading3 = new Unloading();
            unloading3.setId(3);
            unloading3.setDate(LocalDate.now().plusDays(3));
            unloading3.setTime(LocalTime.of(16, 0));
            unloading3.setAddress(addr5);
            unloadingRepository.save(unloading3);

            System.out.println("✅ Unloading schedules created!");
        }

        if (orderRepository.count() == 0) {
            Carrier carrier1 = carrierRepository.findById(1).orElse(null);
            Carrier carrier2 = carrierRepository.findById(2).orElse(null);
            Carrier carrier3 = carrierRepository.findById(3).orElse(null);
            Cargo cargo1 = cargoRepository.findById(1).orElse(null);
            Cargo cargo2 = cargoRepository.findById(2).orElse(null);
            Cargo cargo3 = cargoRepository.findById(3).orElse(null);
            Loading loading1 = loadingRepository.findById(1).orElse(null);
            Loading loading2 = loadingRepository.findById(2).orElse(null);
            Loading loading3 = loadingRepository.findById(3).orElse(null);
            Unloading unloading1 = unloadingRepository.findById(1).orElse(null);
            Unloading unloading2 = unloadingRepository.findById(2).orElse(null);
            Unloading unloading3 = unloadingRepository.findById(3).orElse(null);

            // Create Orders
            Orders order1 = new Orders();
            order1.setId(1);
            order1.setOrderdate(LocalDate.now().minusDays(5));
            order1.setStatus("Delivered");
            order1.setCost(15000.00);
            order1.setCarrier(carrier1);
            order1.setCargo(cargo1);
            order1.setLoading(loading1);
            order1.setUnloading(unloading1);
            orderRepository.save(order1);

            Orders order2 = new Orders();
            order2.setId(2);
            order2.setOrderdate(LocalDate.now().minusDays(3));
            order2.setStatus("In Transit");
            order2.setCost(25000.00);
            order2.setCarrier(carrier1);
            order2.setCargo(cargo2);
            order2.setLoading(loading2);
            order2.setUnloading(unloading2);
            orderRepository.save(order2);

            Orders order3 = new Orders();
            order3.setId(3);
            order3.setOrderdate(LocalDate.now().minusDays(1));
            order3.setStatus("Pending");
            order3.setCost(18000.00);
            order3.setCarrier(carrier2);
            order3.setCargo(cargo3);
            order3.setLoading(loading3);
            order3.setUnloading(unloading3);
            orderRepository.save(order3);

            Orders order4 = new Orders();
            order4.setId(4);
            order4.setOrderdate(LocalDate.now().minusDays(7));
            order4.setStatus("Delivered");
            order4.setCost(22000.00);
            order4.setCarrier(carrier2);
            order4.setCargo(cargo1);
            order4.setLoading(loading1);
            order4.setUnloading(unloading2);
            orderRepository.save(order4);

            Orders order5 = new Orders();
            order5.setId(5);
            order5.setOrderdate(LocalDate.now());
            order5.setStatus("Pending");
            order5.setCost(30000.00);
            order5.setCarrier(carrier3);
            order5.setCargo(cargo2);
            order5.setLoading(loading2);
            order5.setUnloading(unloading1);
            orderRepository.save(order5);

            Orders order6 = new Orders();
            order6.setId(6);
            order6.setOrderdate(LocalDate.now().minusDays(4));
            order6.setStatus("In Transit");
            order6.setCost(19500.00);
            order6.setCarrier(carrier3);
            order6.setCargo(cargo3);
            order6.setLoading(loading3);
            order6.setUnloading(unloading3);
            orderRepository.save(order6);

            System.out.println("✅ Orders created!");
        }

        System.out.println("\n🎉 All dummy data initialized successfully!");
    }
}
