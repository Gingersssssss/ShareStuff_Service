package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class ShareStuffController {

    @Autowired private ItemRepository itemRepo;
    @Autowired private BorrowRecordRepository borrowRepo;
    @Autowired private ReturnRequestRepository returnRepo;
    @Autowired private InvitationRepository inviteRepo;

    // Service 1: ค้นหาของในระบบ (K)
    @GetMapping("/items")
    public List<Item> search(@RequestParam String name, @RequestParam String category) {
        return itemRepo.findByNameContainingAndCategoryContaining(name, category);
    }

    // Service 2: ยืมของในระบบ (K)
    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestParam Long itemId, @RequestParam String user) {
        Item item = itemRepo.findById(itemId).orElseThrow();
        if (item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity() - 1);
            itemRepo.save(item);

            BorrowRecord record = new BorrowRecord();
            record.setItemId(itemId);
            record.setBorrowedBy(user);
            record.setBorrowedAt(LocalDateTime.now());
            borrowRepo.save(record);

            return ResponseEntity.ok("Borrowed by " + user);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Out of stock");
    }

    // Service 3: ส่งคำขอคืนของ (N)
    @PostMapping("/return-request")
    public ResponseEntity<String> returnRequest(@RequestParam Long itemId, @RequestParam String fromUser, @RequestParam String toUser) {
        ReturnRequest req = new ReturnRequest();
        req.setItemId(itemId);
        req.setFromUser(fromUser);
        req.setToUser(toUser);
        req.setRequestedAt(LocalDateTime.now());
        returnRepo.save(req);
        return ResponseEntity.ok("Return request sent from " + fromUser + " to " + toUser);
    }

    // Service 4: ส่งคำขอเชิญเข้าร่วมระบบ (N)
    @PostMapping("/invite")
    public ResponseEntity<String> invite(@RequestParam String fromUser, @RequestParam String toUser, @RequestParam String contact) {
        Invitation inv = new Invitation();
        inv.setFromUser(fromUser);
        inv.setToUser(toUser);
        inv.setContact(contact);
        inv.setSentAt(LocalDateTime.now());
        inviteRepo.save(inv);
        return ResponseEntity.ok("Invitation sent to " + toUser);
    }

    // เพิ่ม endpoint ตรวจสอบคำขอ (ใช้ log/debug บนฝั่ง client)
    @GetMapping("/requests/to/{user}")
    public Map<String, Object> getRequests(@PathVariable String user) {
        List<ReturnRequest> returnRequests = returnRepo.findByToUser(user);
        List<Invitation> invitations = inviteRepo.findByToUser(user);

        Map<String, Object> result = new HashMap<>();
        result.put("returnRequests", returnRequests);
        result.put("invitations", invitations);

        return result;
    }
}