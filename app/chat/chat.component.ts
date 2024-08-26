import { Component } from '@angular/core';
import { OpenaiService } from '../openai.service';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  prompt: string = '';
  response: string = '';

  constructor(private openaiService: OpenaiService) { }

  sendPrompt() {
    this.openaiService.getOpenAIResponse(this.prompt).subscribe({
      next: (data) => {
        this.response = data;
      },
      error: (error) => {
        console.error('Error fetching OpenAI response', error);
      }
    });
  }
}
