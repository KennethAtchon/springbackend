package com.example.demo.controllers.api;

public class AuthController {
    
}



// export const getMessages = async (req, res) => {
//     try {
//       const { sender, recipient } = req.query;
  
//       let chats = await ChatMessage.findOne({
//         sender: sender,
//         recipient: recipient
//       });
  
//       if (!chats) {
//         chats = await ChatMessage.findOne({
//           sender: recipient,
//           recipient: sender
//         });
  
//         return res.json({
//           sender: chats.recipient,
//           recipient: chats.sender,
//           messages: chats.message
//         });
  
  
//       }
  
//       if (!chats) {
//         return res.status(404).json({ message: 'No messages found' });
//       }
  
//       res.json({
//         sender: chats.sender,
//         recipient: chats.recipient,
//         messages: chats.message
//       });
//     } catch (err) {
//       res.status(500).json({ message: err.message });
//     }
//   };