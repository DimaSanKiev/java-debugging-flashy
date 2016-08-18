package com.teamtreehouse.flashy.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.teamtreehouse.flashy.App;
import com.teamtreehouse.flashy.domain.FlashCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@DatabaseSetup("classpath:flashCards.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class FlashCardRepositoryTest {
  @Autowired
  private FlashCardRepository repository;

  @Test
  public void findAll_ShouldReturnThree() throws Exception {
    assertThat(repository.findAll(), hasSize(3));
  }

  @Test
  public void save_ShouldPersistEntity() throws Exception {
    FlashCard flashCard = new FlashCard();
    repository.save(flashCard);
    assertThat(repository.findOne(flashCard.getId()), notNullValue(FlashCard.class));
  }
}