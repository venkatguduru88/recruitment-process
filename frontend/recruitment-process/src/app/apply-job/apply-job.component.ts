import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { ApplyJobService } from '../services/apply-job.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-apply-job',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './apply-job.component.html',
  styleUrl: './apply-job.component.css',
})
export class ApplyJobComponent {
  form: FormGroup;
  selectedFile: File | null = null;
  fileError: string = '';

  constructor(private fb: FormBuilder, private jobService: ApplyJobService) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (!file) {
      this.fileError = 'Please upload a file.';
      this.selectedFile = null;
      return;
    }

    const allowedTypes = [
      'application/pdf',
      'application/msword',
      'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
    ];

    if (!allowedTypes.includes(file.type)) {
      this.fileError = 'Only PDF or Word documents are allowed.';
      this.selectedFile = null;
    } else {
      this.fileError = '';
      this.selectedFile = file;
    }
  }

  onSubmit() {
    if (this.form.valid && this.selectedFile) {
      console.log('Form Data:', this.form.value);
      console.log('Uploaded File:', this.selectedFile.name);
      alert('Form submitted successfully!');
      const formData = new FormData();
      const data = {
        name: this.form.value.name,
        email: this.form.value.email,
      };
      formData.append(
        'data',
        new Blob([JSON.stringify(data)], { type: 'application/json' })
      );
      formData.append('file', this.selectedFile);

      this.jobService.submitApplication(formData).subscribe({
        next: () => alert('Application submitted!'),
        error: () => alert('Error submitting application.'),
      });
    } else {
      this.form.markAllAsTouched();
      this.fileError ||= 'Please upload a valid file.';
    }
  }
}
