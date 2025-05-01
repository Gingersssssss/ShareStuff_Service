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


    // Service 1: ส่งคำขอดูรายการของที่ยืมในระบบ (N)
    @PostMapping("/return-stuff")
    //แก้โค้ดให้ถูกต้อง
    public ResponseEntity<?> invite(@RequestParam String fromUser, @RequestParam String toUser, @RequestParam String contact) {
        Invitation inv = new Invitation();
        inv.setFromUser(fromUser);
        inv.setToUser(toUser);
        inv.setContact(contact);
        inv.setSentAt(LocalDateTime.now());
        inviteRepo.save(inv);

        return ResponseEntity.ok(EntityModel.of(inv,
            linkTo(methodOn(ShareStuffController.class).getRequests(toUser)).withRel("requests")));
    }

    // Service 2: ส่งคำขอคืนของ (N)
    @PostMapping("/return-request")
    public ResponseEntity<?> returnRequest(@RequestParam Long itemId, @RequestParam String fromUser, @RequestParam String toUser) {
        ReturnRequest req = new ReturnRequest();
        req.setItemId(itemId);
        req.setFromUser(fromUser);
        req.setToUser(toUser);
        req.setRequestedAt(LocalDateTime.now());
        returnRepo.save(req);

        return ResponseEntity.ok(EntityModel.of(req,
            linkTo(methodOn(ShareStuffController.class).getRequests(toUser)).withRel("requests")));
    }


    // Endpoint: ดูคำขอที่ส่งถึงผู้ใช้ (สำหรับ K ตรวจดู)
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