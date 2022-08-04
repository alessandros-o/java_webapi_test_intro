package br.com.iteris.universidade.testes.intro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private UUID id;
    private String name;
    private String email;
    private String document;
    private CountryEnum country;
}
