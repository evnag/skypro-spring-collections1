package ru.skypro.skyprospringcollections1.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.exception.IncorrectParamException;

@Service
public class ValidatorService {

    public String validateFirstName(String firstName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new IncorrectParamException();
        }
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public String validateLastName(String lastName) {
        String[] lastNames = lastName.split("-");
        for (int i = 0; i < lastNames.length; i++) {
            if (!StringUtils.isAlpha(lastNames[i])) {
                throw new IncorrectParamException();
            }
            lastNames[i] = StringUtils.capitalize(lastName.toLowerCase());
        }
        return String.join("-", lastNames);
    }
}
