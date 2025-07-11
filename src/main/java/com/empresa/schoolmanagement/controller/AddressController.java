package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.AddressDTO;
import com.empresa.schoolmanagement.mapper.AddressMapper;
import com.empresa.schoolmanagement.model.Address;
import com.empresa.schoolmanagement.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(addressDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Address address = addressService.findById(id);
        AddressDTO addressDTO = addressMapper.toDTO(address);
        return ResponseEntity.ok(addressDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        Address savedAddress = addressService.save(address);
        AddressDTO savedAddressDTO = addressMapper.toDTO(savedAddress);
        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        Address updatedAddress = addressService.update(id, address);
        AddressDTO updatedAddressDTO = addressMapper.toDTO(updatedAddress);
        return ResponseEntity.ok(updatedAddressDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}