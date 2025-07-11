package com.empresa.schoolmanagement.mapper;


import com.empresa.schoolmanagement.dto.AddressDTO;
import com.empresa.schoolmanagement.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(Address address);
    Address toEntity(AddressDTO dto);
}
