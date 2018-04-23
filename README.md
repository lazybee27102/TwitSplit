# Project Assignment - *TwitSplit*

**TwitSplit** is an android app that allows users to post short messages limited to 50 characters each. Sometimes, users get excited and write messages longer than 50 characters.

Instead of rejecting these messages, we would like to add a new feature that will split the message into parts and send multiple messages on the user's behalf, all of them meeting the 50 character requirement.

Time spent: **4** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X]	Allow the user to input and send messages.
* [X]	Display the user's messages.
* [X]  If a user's input is less than or equal to 50 characters, post it as is.
* [X]  If a user's input is greater than 50 characters, split it into chunks that each is less than or equal to 50 characters and post each chunk as a separate message.
* [X] Messages will only be split on whitespace. If the message contains a span of non-whitespace characters longer than 50 characters, display an error.
* [X] Split messages will have a "part indicator" appended to the beginning of each section. In the example above, the message was split into two chunks, so the part indicators read "1/2" and "2/2". Be aware that these count toward the character limit.
* [X] Unit Testing 

## Open-source libraries used

- [RxJava2](https://github.com/ReactiveX/RxJava)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Dagger2](https://google.github.io/dagger/)

## License

    Copyright [2018] [Nguyen Hoang Phat]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
