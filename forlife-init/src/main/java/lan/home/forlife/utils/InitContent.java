package lan.home.forlife.utils;

import lan.home.forlife.domain.*;
import lan.home.forlife.domain.Article;
import lan.home.forlife.repositories.ArticleRepository;
import lan.home.forlife.repositories.GroupRepository;
import lan.home.forlife.repositories.SubjectRepository;
import lan.home.forlife.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by yar on 20.01.15.
 */
@Component
public class InitContent {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @PostConstruct
    public void addDefaultUsers(){
        Group admin = new Group("Admin");
        admin.getRoles().add(Role.ADMIN);
        Group user = new Group("user");
        user.getRoles().add(Role.USER);
        groupRepository.save(Arrays.asList(new Group[]{admin, user}));
        User yar = new User();
        yar.setUsername("yar");
        yar.setPassword(passwordEncoder.encode("123456"));
        yar.getGroups().add(admin);
        userRepository.save(yar);


        Subject test1 = new Subject();
        test1.setName("testSubject1");
        Subject test2 = new Subject();
        test2.setName("testSubject2");
        subjectRepository.save(Arrays.asList(new Subject[]{test1, test2}));

        Article article = new Article();
        article.setName("testArticle");
        article.setType(ElementType.ARTICLE);
        article.setSubject(test1);
//        article.setContent("<p style=\"text-align: center;\">Test content</p>\n" +
//                "<ul>\n" +
//                "<li style=\"text-align: left;\">First</li>\n" +
//                "<li style=\"text-align: left;\">Secont</li>\n" +
//                "<li style=\"text-align: left;\">Third</li>\n" +
//                "<li style=\"text-align: left;\"><em>fsfdsfsfsdf</em></li>\n" +
//                "</ul>");
        article.setContent("<h1>Content 1</h1>");
        Article article1 = new Article();
        article1.setType(ElementType.ARTICLE);
        article1.setName("testBBB");
        article1.setContent("<h2>Content 2</h2>");
        articleRepository.save(Arrays.asList(new Article[]{article, article1}));
    }
}
