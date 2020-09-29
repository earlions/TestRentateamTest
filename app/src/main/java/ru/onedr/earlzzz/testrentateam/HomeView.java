package ru.onedr.earlzzz.testrentateam;

import java.util.List;

import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public interface HomeView {
    interface View {
        void showText(List<Post> posts);
        void offLoad();
    }

    interface Presenter {
        void onLoadBD();
    }

    interface Repository {
        void loadMessage();
    }
}
