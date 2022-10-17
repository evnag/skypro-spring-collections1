package ru.skypro.skyprospringcollections1.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.exception.IncorrectParamException;

@Service
public class ValidatorService {

    public String validate(String param) {
        if (!StringUtils.isAlpha(param)) {
            throw new IncorrectParamException();
        }
        return StringUtils.capitalize(param.toLowerCase());
    }
}
