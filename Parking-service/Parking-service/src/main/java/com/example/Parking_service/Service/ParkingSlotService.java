package com.example.Parking_service.Service;

import com.example.Parking_service.Config.ParkingMessageProducer;
import com.example.Parking_service.Model.ParkingSlot;
import com.example.Parking_service.Repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ParkingMessageProducer messageProducer;

    public ResponseEntity<ParkingSlot> createParkingSlot(ParkingSlot parkingSlot) {
        ParkingSlot savedParkingSlot = parkingSlotRepository.save(parkingSlot);
        return new ResponseEntity<>(savedParkingSlot, HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<ParkingSlot>> getParkingSlotById(Long id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(id);
        if (parkingSlot.isPresent()) {
            return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ParkingSlot>> getAllParkingSlots() {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();
        return new ResponseEntity<>(parkingSlots, HttpStatus.OK);
    }

    public ResponseEntity<ParkingSlot> updateParkingSlot(Long id, ParkingSlot updatedParkingSlot) {
        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking slot not found"));
        parkingSlot.setSlotNumber(updatedParkingSlot.getSlotNumber());
        parkingSlot.setAvailable(updatedParkingSlot.isAvailable());
        ParkingSlot savedParkingSlot = parkingSlotRepository.save(parkingSlot);
        return new ResponseEntity<>(savedParkingSlot, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteParkingSlot(Long id) {
        if (parkingSlotRepository.existsById(id)) {
            parkingSlotRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ParkingSlot> allocateParkingSlot(Long id) {
        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking slot not found"));
        if (!parkingSlot.isAvailable()) {
            throw new RuntimeException("Parking slot is not available");
        }
        parkingSlot.setAvailable(false);
        ParkingSlot allocatedSlot = parkingSlotRepository.save(parkingSlot);
        messageProducer.sendMessage("parking_queue","Parking slotnumber"+parkingSlot.getSlotNumber()+"successfully reserved");
        return new ResponseEntity<>(allocatedSlot, HttpStatus.OK);
    }

    public ResponseEntity<ParkingSlot> releaseParkingSlot(Long id) {
        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking slot not found"));
        parkingSlot.setAvailable(true);
        ParkingSlot releasedSlot = parkingSlotRepository.save(parkingSlot);
        messageProducer.sendMessage("parking_queue","Parking slotnumber"+parkingSlot.getSlotNumber()+"successfully released");
        return new ResponseEntity<>(releasedSlot, HttpStatus.OK);
    }
}