package twitsplit.project.bee.twitsplit.ui.main.tweet;

import java.util.ArrayList;
import java.util.List;

import twitsplit.project.bee.twitsplit.data.pojo.Message;
import twitsplit.project.bee.twitsplit.exception.TwitOutOfRangeException;

public class MessageSplitter {
    private static final int MAX_LENGTH = 50;
    private final List<String> messageList = new ArrayList<>();

    public List<String> getMessageList() {
        return messageList;
    }

    public static List<String> splitMessage(Message message) throws TwitOutOfRangeException, NullPointerException {
        return new MessageSplitter(message).getMessageList();
    }


    public MessageSplitter(Message message) throws TwitOutOfRangeException, NullPointerException {
        if (message == null || message.getContent() == null) {
            throw new NullPointerException();
        } else {
            split(message);
        }
    }

    private void split(Message message) throws TwitOutOfRangeException {
        String content = message.getContent().trim();
        int contentLen = content.trim().length();

        if (contentLen == 0) {
            return;
        }

        if (contentLen <= MAX_LENGTH) {
            messageList.add(content);
            return;
        }

        splitMessage(content);
    }

    private void splitMessage(String content) throws TwitOutOfRangeException {
        int contentLen = content.length();

        int curCountDigit = countDigit(content.length() / MAX_LENGTH);
        int indexPart = 1;
        int indexBegin = 0;
        int indicatorCharacterCount = countDigit(indexPart) + 1 + curCountDigit + 1;
        int indexEnd = indexBegin + MAX_LENGTH - indicatorCharacterCount;

        while (indexEnd < content.length()) {
            int indexWhiteSpace = 0;
            boolean isFoundWhiteSpace = false;

            // Detect if there is a white space in content
            for (int i = indexEnd; i > indexBegin; i--) {
                if (content.charAt(i) == ' ') {
                    isFoundWhiteSpace = true;
                    indexWhiteSpace = i;
                    break;
                }
            }

            if (isFoundWhiteSpace) {
                String messagePart = content.substring(indexBegin, indexWhiteSpace);
                messageList.add(messagePart);

                indexPart += 1;

                indexBegin = indexWhiteSpace + 1;

                indicatorCharacterCount = countDigit(indexPart) + 1 + curCountDigit + 1;

                indexEnd = indexBegin + MAX_LENGTH - indicatorCharacterCount;
            } else {
                throw new TwitOutOfRangeException();
            }
        }

        // Index end is bigger than content length
        if (indexBegin < contentLen) {
            String messagePart = content.substring(indexBegin, contentLen);
            messageList.add(messagePart);
        }

        formatMessageList();
    }

    private void formatMessageList() {
        int size = messageList.size();
        for (int i = 0; i < size; i++) {
            String newContent = String.format("[%d/%d] %s", i + 1, size, messageList.get(i));
            messageList.set(i, newContent);
        }
    }

    private static int countDigit(int index) {
        if (index == 0) {
            return 0;
        }

        return 1 + countDigit(index / 10);
    }
}
