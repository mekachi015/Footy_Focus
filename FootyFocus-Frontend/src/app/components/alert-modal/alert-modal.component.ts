import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-alert-modal',
  templateUrl: './alert-modal.component.html',
  styleUrls: ['./alert-modal.component.scss']
})
export class AlertModalComponent {
  @Input() error: string | null = null;
  @Input() success: string | null = null;
  @Output() close = new EventEmitter<void>();

  onCloseClick() {
    this.close.emit();
  }
}
