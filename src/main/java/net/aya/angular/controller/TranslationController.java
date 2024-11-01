package net.aya.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class TranslationController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/translations")
    public Map<String, String> getTranslations(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        Locale locale = new Locale(lang);
        Map<String, String> translations = new HashMap<>();

        // Fetching translations for accounts
        translations.put("accounts.title", messageSource.getMessage("accounts.title", null, locale));
        translations.put("accounts.accountId", messageSource.getMessage("accounts.accountId", null, locale));
        translations.put("accounts.balance", messageSource.getMessage("accounts.balance", null, locale));
        translations.put("accounts.table.id", messageSource.getMessage("accounts.table.id", null, locale));
        translations.put("accounts.table.date", messageSource.getMessage("accounts.table.date", null, locale));
        translations.put("accounts.table.type", messageSource.getMessage("accounts.table.type", null, locale));
        translations.put("accounts.table.amount", messageSource.getMessage("accounts.table.amount", null, locale));
        translations.put("actions.search", messageSource.getMessage("actions.search", null, locale));
        translations.put("loading.message", messageSource.getMessage("loading.message", null, locale));
        translations.put("operations.title", messageSource.getMessage("operations.title", null, locale));
        translations.put("operations.debit", messageSource.getMessage("operations.debit", null, locale));
        translations.put("operations.credit", messageSource.getMessage("operations.credit", null, locale));
        translations.put("operations.transfer", messageSource.getMessage("operations.transfer", null, locale));
        translations.put("operations.accountDestination", messageSource.getMessage("operations.accountDestination", null, locale));
        translations.put("operations.amount", messageSource.getMessage("operations.amount", null, locale));
        translations.put("operations.description", messageSource.getMessage("operations.description", null, locale));
        translations.put("operations.save", messageSource.getMessage("operations.save", null, locale));

        // Fetching translations for customers
        translations.put("customers.title", messageSource.getMessage("customers.title", null, locale));
        translations.put("customers.table.id", messageSource.getMessage("customers.table.id", null, locale));
        translations.put("customers.table.name", messageSource.getMessage("customers.table.name", null, locale));
        translations.put("customers.table.email", messageSource.getMessage("customers.table.email", null, locale));
        translations.put("customers.actions.search", messageSource.getMessage("customers.actions.search", null, locale));
        translations.put("customers.actions.delete", messageSource.getMessage("customers.actions.delete", null, locale));
        translations.put("customers.actions.accounts", messageSource.getMessage("customers.actions.accounts", null, locale));
        translations.put("customers.message", messageSource.getMessage("customers.message", null, locale));
        translations.put("customers.loading.error", messageSource.getMessage("customers.loading.error", null, locale));

        translations.put("customers.form.name", messageSource.getMessage("customers.form.name", null, locale)); // New Translation
        translations.put("customers.form.email", messageSource.getMessage("customers.form.email", null, locale)); // New Translation
        translations.put("customers.form.name.required", messageSource.getMessage("customers.form.name.required", null, locale)); // New Translation
        translations.put("customers.form.email.invalid", messageSource.getMessage("customers.form.email.invalid", null, locale)); // New Translation
        translations.put("customers.form.save", messageSource.getMessage("customers.form.save", null, locale)); // New Translation
        translations.put("error.form.message", messageSource.getMessage("error.form.message", null, locale)); // New Translation




        return translations;
    }
}
