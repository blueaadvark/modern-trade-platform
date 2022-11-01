package com.crd.service.tradeservice.app;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
class TradeServiceImpl implements TradeService {

  private final OwnerRepository ownerRepository;
  private final ProjectRepository projectRepository;

  /** Some code for my local tests. Please remove. */
  @Override
  public void testMethods() {
    // one of 5 possible UUIDs to be reused in updates
    var scopedDigit = LocalDateTime.now().getSecond() % 2 + 1; // (from 1 to 2)
    var scopedAsString = "00000000-0000-0000-0000-00000000000" + scopedDigit;
    var scopedUuid = UUID.fromString(scopedAsString);

    var exampleNewOwner = new OwnerDbo()
        .setEntityId(scopedUuid)
        .setName("initial name");
    var exampleOwner = ownerRepository
        .findById(scopedUuid)
        .orElseGet(() -> ownerRepository.save(exampleNewOwner));
    // create or update example record
    var exampleNewProject = new ProjectDbo()
        .setEntityId(scopedUuid)
        .setOwner(exampleOwner)
        .setName("initial name");
    var exampleProject = projectRepository.findById(scopedUuid)
        .orElseGet(() -> projectRepository.save(exampleNewProject));
    exampleProject.setName(LocalDateTime.now().toString());
    projectRepository.save(exampleProject);
  }

}
