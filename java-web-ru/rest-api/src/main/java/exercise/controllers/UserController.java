package exercise.controllers;

import io.javalin.core.validation.BodyValidator;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());

        BodyValidator<User> userValidator = ctx.bodyValidator(User.class)
                .check(it -> !it.getFirstName().isEmpty(), "Имя не должно быть пустым")
                .check(it -> !it.getLastName().isEmpty(), "Фамилия не должна быть пустой")
                .check(it -> StringUtils.isNumeric(it.getPassword()), "пароль содержит только цифры")
                .check(it -> it.toString().length() >= 4, "Пароль не короче 4 символов")
                .check(it -> EmailValidator.getInstance().isValid(it.getEmail()), "email не корректен");

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();
        user.delete();
        // END
    };
}
