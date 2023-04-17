package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.validation.constraints.Null;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main extends TelegramLongPollingBot {

    private Map<Long, Integer> levels = new HashMap<>();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        return "Oleksandr_Radionenko_InfoBot";
    }

    @Override
    public String getBotToken() {
        return "6081415769:AAFug3-J_BbEf5p5GmMyqLepu5icYvsz7BA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            SendMessage message = createMessage("Hello!\n" +
                    "My name is Oleksandr Radionenko.\n" +
                    "This is my telegram bot in which you can read main information about me.\n" +
                    "Choose the topic and we will continue!");
            message.setChatId(chatId);


            attachButtons(message, Map.of(
                    "CV Photo", "photo_lvl"
            ));
            sendApiMethodAsync(message);
        }



        if(update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("photo_lvl") && getLevel(chatId) == 1){

                setLevel(chatId, 2);

                sendImage("aaaaaa", chatId);

                SendMessage message = createMessage("Yeah, that's me!");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "CV Statement", "statement_lvl"
                ));
                sendApiMethodAsync(message);

            }



            if (update.getCallbackQuery().getData().equals("statement_lvl") && getLevel(chatId) == 2){

                setLevel(chatId, 3);

                SendMessage message = createMessage("I am eager to get a job to prove myself as a smart, motivated employee and a strong team member. My goal is to take the most useful knowledge from this internship and implement my skills and ideas in future projects of your company. I am very inspired by the opportunity to make technology more accessible and engaging for people.\n" +
                        "\n" +
                        "In my opinion, Your company is the most appropriate company for this!:))\n");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                            "Education", "edu_lvl"
                ));
                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("edu_lvl") && getLevel(chatId) == 3){

                setLevel(chatId, 4);

                SendMessage message = createMessage("Wroclaw University of Science and Technologies, Poland\n\n" +
                        "Management\n\n" +
                        "October 2022 – present\n" +
                        "\n\n" +
                        "National Technical University of Ukraine \"Sikorsky Polytechnic\"\n\n" +
                        "Computer Engineering\n\n" +
                        "September 2022 – present\n");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Projects", "proj_lvl"
                ));
                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("proj_lvl") && getLevel(chatId) == 4){

                setLevel(chatId, 5);

                SendMessage message = createMessage("You can look at my CV-Site, which I made using HTML and CSS.\n" +
                        "There you will find GIT link and some of my projects!\n\n" +
                        "https://oleksandr-radionenko-cvsite.netlify.app");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Tech Skills", "tech_skills_lvl"
                ));

                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("tech_skills_lvl") && getLevel(chatId) == 5){

                setLevel(chatId, 6);

                SendMessage message = createMessage("•\tPython\n" +
                        "•\tC++\n" +
                        "•\tJAVA\n" +
                        "•\tHTML/CSS\n");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Soft Skills", "soft_skills_lvl"
                ));

                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("soft_skills_lvl") && getLevel(chatId) == 6){

                setLevel(chatId, 7);

                SendMessage message = createMessage("•\tOrganizational skills\n" +
                        "•\tQuick learning\n" +
                        "•\tAttention to detail\n" +
                        "•\tWillingness to learn \n");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Languages", "lang_lvl"
                ));

                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("lang_lvl") && getLevel(chatId) == 7){

                setLevel(chatId, 8);

                SendMessage message = createMessage("•\tB2 – English\n" +
                        "•\tB2 - Polish\n" +
                        "•\tNative - Ukrainian\n" +
                        "•\tFluent – Russian");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Final Statement", "final_lvl"
                ));

                sendApiMethodAsync(message);
            }



            if (update.getCallbackQuery().getData().equals("final_lvl") && getLevel(chatId) == 8){

                setLevel(chatId, 9);

                SendMessage message = createMessage("You can contact me using:\n" +
                        "Phone: +48 (537) 143 889\n" +
                        "Mail: oleksandr.g.radionenko@gmail.com\n" +
                        "I am glad that you got to know more about me!\n" +
                        "Thanks for your attention!:)");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
        }
    }

    public long getChatId(Update update) {
        if (update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return 0;
    }

    public SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage (String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".jpg"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }

    public int getLevel (Long chatId){
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level){
        levels.put(chatId, level);
    }

}

