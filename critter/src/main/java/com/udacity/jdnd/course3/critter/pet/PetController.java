package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    // Autowire PetService
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        // PetDTO to Pet
        Pet pet = convertPetDTOToPet(petDTO);

        // Save pet
        Pet savedPet = petService.save(pet);

        // Pet to PetDTO
        return convertPetToPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        // Find pet by id
        Pet pet = petService.find(petId);

        // Pet to PetDTO
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        // Find pets by customer
        List<Pet> pets = petService.findPetsByCustomer(ownerId);

        // Pet to PetDTO
        return convertPetListToPetDTOList(pets);
    }

    // create convertPetDTOToPet

    public Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setCustomer(customerService.find(petDTO.getOwnerId()));
        pet.setType(petDTO.getType());
        return pet;
    }

    // create convertPetToPetDTO

    public PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(pet.getType());
        return petDTO;
    }

    // create convertPetListToPetDTOList

    public List<PetDTO> convertPetListToPetDTOList(List<Pet> pets){
        List<PetDTO> petDTOs = new ArrayList<PetDTO>();
        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pet.getId());
            petDTO.setName(pet.getName());
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setNotes(pet.getNotes());
            petDTO.setOwnerId(pet.getCustomer().getId());
            petDTO.setType(pet.getType());
            petDTOs.add(petDTO);
        }
        return petDTOs;


    }
}
