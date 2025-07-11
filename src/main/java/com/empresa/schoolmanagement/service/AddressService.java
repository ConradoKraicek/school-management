package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.exception.NotFoundException;
import com.empresa.schoolmanagement.model.Address;
import com.empresa.schoolmanagement.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado, id: " + id));
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Long id, Address addressDetails) {
        Address address = findById(id);
        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setNumber(addressDetails.getNumber());
        address.setZipcode(addressDetails.getZipcode());
        return addressRepository.save(address);
    }

    public void delete(Long id) {
        Address address = findById(id);
        addressRepository.delete(address);
    }
}