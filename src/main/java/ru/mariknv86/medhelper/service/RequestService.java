package ru.mariknv86.medhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.mariknv86.medhelper.dto.request.RequestDTO;
import ru.mariknv86.medhelper.exceptions.NotFoundException;
import ru.mariknv86.medhelper.model.Request;
import ru.mariknv86.medhelper.repository.RequestRepo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {

    private final RequestRepo requestRepo;

    public Long addNewRequest(RequestDTO requestDTO) {
        Request request = new Request();

        request.setQuestion(requestDTO.getQuestion());
        request.setAnswer(requestDTO.getAnswer());
        request.setNote(requestDTO.getNote());
        request.setUrl(requestDTO.getUrl());
        requestRepo.save(request);
        return request.getId();
    }

    public Request getById(Long id) {
        return requestRepo.findById(id).
                orElseThrow(NotFoundException::new);
    }

    public List<Request> getByQuestion(String question) {
        return requestRepo.findRequestByQuestion(question);
    }

    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }

    public void editRequest(RequestDTO requestDTO, Request request) {
        request.setQuestion(requestDTO.getQuestion());
        request.setAnswer(requestDTO.getAnswer());
        request.setNote(requestDTO.getNote());
        request.setUrl(requestDTO.getUrl());
        requestRepo.save(request);
    }

    public void deleteRequest(Long id) {
        Request request = requestRepo.findById(id).
                orElseThrow(NotFoundException::new);
        requestRepo.delete(request);
    }

    public void loadFromFile(String file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(file));

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList requests = doc.getElementsByTagName("request");
        int requestCount = requests.getLength();

        System.out.println(requestCount);

        for (int i = 0; i < requestCount; i++) {
            Node node = requests.item(i);
            System.out.println(node.getNodeName());
            //NamedNodeMap attributes = node.getAttributes();

            Element element = (Element) node;

            String question = element.getElementsByTagName("question").item(0).getTextContent();
            String answer = element.getElementsByTagName("answer").item(0).getTextContent();
            String note = element.getElementsByTagName("note").item(0).getTextContent();
            String url = element.getElementsByTagName("url").item(0).getTextContent();

            Request request = new Request();
            request.setQuestion(question);
            request.setAnswer(answer);
            request.setNote(note);
            request.setUrl(url);
            requestRepo.save(request);
        }
    }





}
