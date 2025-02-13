package com.beyond.di.character;

import com.beyond.di.config.RootConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
// @ContextConfiguration(locations = "classpath:spring/root-context.xml")
@ContextConfiguration(classes = RootConfig.class)
class CharacterTest {
    /*
     @Autowired 어노테이션을 통해서 주입받을 빈이 존재하지 않으면 에러가 발생한다.
       - required는 빈 주입이 필수로 진행되어야 하는지 설정하는 속성이다. (기본값: true)
         ture일 경우 주입해야 하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 예외가 발생한다.
         false일 경우 주입해야 하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 null 주입한다.
     */
    @Autowired(required = false)
    private Character character;

    @Value("${db.driver:not found}")
    private String driver;

    @Value("${db.url:not found}")
    private String url;

    @Test
    @Disabled
    void nothing(){
    }

    @Test
    void create(){
        System.out.println(character);

        assertThat(character).isNotNull();
        assertThat(character.getLevel()).isGreaterThan(0);
        assertThat(character.getWeapon()).isNotNull();
    }

    @Test
    void propertyTest(){
        System.out.println(driver + " " + url);

        assertThat(driver).isEqualTo("org.mariadb.jdbc.Driver");
        assertThat(url).isEqualTo("jdbc:mariadb://localhost:3306/web");
    }

}